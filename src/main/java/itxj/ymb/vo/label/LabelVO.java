package itxj.ymb.vo.label;

import itxj.ymb.pojo.Label;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 文章标签信息结果
 */
@Getter
@Setter
@ToString
public class LabelVO implements Serializable {
	private Label label;

	public LabelVO(Label label) {
		this.label = label;
	}
}
