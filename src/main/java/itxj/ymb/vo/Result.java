package itxj.ymb.vo;

import itxj.ymb.constant.APIConstant;
import itxj.ymb.util.DataUtils;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装业务响应工具类，使用此工具类的接口返回的HTTP状态码都应该是200
 */
public class Result<T> {
    /**
     * 生成响应内容
     *
     * @param apiConstant APIConstant
     * @param data        响应的数据
     * @return 返回Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    private T generateResponseBody(APIConstant apiConstant, T data) {
        Map<String, Object> mapBody = new HashMap<>();
        mapBody.put("code", apiConstant.getCode());
        mapBody.put("message", apiConstant.getMessage());
        if (data != null) {
            mapBody.put("data", data);
        }
        return (T) mapBody;
    }

    /**
     * 生成不带有数据的响应体
     *
     * @param apiConstant APIConstant
     * @return 返回ResponseEntity<?>
     */
    public ResponseEntity<T> generateResponseEntity(APIConstant apiConstant) {
        return ResponseEntity.ok().body(generateResponseBody(apiConstant, null));
    }

    /**
     * 生成带有数据的响应体
     *
     * @param apiConstant APIConstant
     * @param data        响应数据
     * @return 返回ResponseEntity<?>
     */
    public ResponseEntity<T> generateResponseEntity(APIConstant apiConstant, T data) {
        return ResponseEntity.ok().body(generateResponseBody(apiConstant, data));
    }

    /**
     * 生成不带有数据的成功响应体
     *
     * @return 返回ResponseEntity<?>
     */
    public ResponseEntity<T> generateSuccessResponseEntity() {
        return ResponseEntity.ok().body(generateResponseBody(APIConstant.SUCCESS, null));
    }

    /**
     * 生成带有数据的成功响应体
     *
     * @param data           响应数据
     * @return 返回ResponseEntity<?>
     */
    public ResponseEntity<T> generateSuccessResponseEntity(T data) {
        return ResponseEntity.ok().body(generateResponseBody(APIConstant.SUCCESS, data));
    }

    /**
     * 生成不带有数据的失败响应体
     *
     * @param failMessage 失败响应的自定义消息，如果为空，则使用默认消息
     * @return 返回ResponseEntity<?>
     */
    public ResponseEntity<T> generateFailResponseEntity(String failMessage) {
        return DataUtils.isStringNotNull(failMessage) ?
                ResponseEntity.ok().body(generateResponseBody(APIConstant.FAIL.setMessage(failMessage), null)) :
                ResponseEntity.ok().body(generateResponseBody(APIConstant.FAIL, null));
    }

    /**
     * 生成带有数据的失败响应体
     *
     * @param failMessage 成功响应的自定义消息，如果为空，则使用默认消息
     * @param data        响应数据
     * @return 返回ResponseEntity<?>
     */
    public ResponseEntity<T> generateFailResponseEntity(String failMessage, T data) {
        return DataUtils.isStringNotNull(failMessage) ?
                ResponseEntity.ok().body(generateResponseBody(APIConstant.FAIL.setMessage(failMessage), data)) :
                ResponseEntity.ok().body(generateResponseBody(APIConstant.FAIL, data));
    }
}
