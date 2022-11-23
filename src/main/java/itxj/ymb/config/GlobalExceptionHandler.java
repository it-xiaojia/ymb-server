package itxj.ymb.config;

import itxj.ymb.exception.AppRuntimeException;
import itxj.ymb.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 控制器全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * 参数校验统一异常处理
	 *
	 * @param exception MethodArgumentNotValidException BindingResult捕获的异常
	 *                  HttpMessageNotReadableException 参数类型非法异常
	 *                  HttpMediaTypeNotSupportedException 不支持的请求体类型异常
	 *                  AppRuntimeException 自定义异常
	 * @return 返回ResponseEntity
	 */
	@ResponseBody
	@ExceptionHandler(value = {MethodArgumentNotValidException.class,
			HttpMessageNotReadableException.class,
			HttpMediaTypeNotSupportedException.class,
			AppRuntimeException.class})
	public ResponseEntity<?> handleException(Exception exception) {
		ResponseEntity<?> result = null;
		if (exception instanceof MethodArgumentNotValidException) {
			BindingResult bindingResult = ((MethodArgumentNotValidException) exception).getBindingResult();
			if (bindingResult.hasErrors()) {
				result = new Result<>().generateFailResponseEntity(bindingResult.getAllErrors().get(0).getDefaultMessage());
			}
		} else if (exception instanceof HttpMessageNotReadableException) {
			exception.printStackTrace();
			result = new Result<>().generateFailResponseEntity("参数类型非法");
		} else if (exception instanceof HttpMediaTypeNotSupportedException) {
			exception.printStackTrace();
			result = new Result<>().generateFailResponseEntity("不支持的请求体类型");
		} else if (exception instanceof AppRuntimeException) {
			result = new Result<>().generateFailResponseEntity(exception.getMessage());
		} else {
			exception.printStackTrace();
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return result;
	}
}
