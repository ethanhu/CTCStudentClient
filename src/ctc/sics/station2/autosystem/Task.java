package ctc.sics.station2.autosystem;


/**
 * 负责处理车站任务
 * 
 * @author 胡恩召
 * 
 */
public class Task {

	public String time;
	public String trainName;
	public String startName;
	public String endName;
	public int flag = 0; //任务状态,0:等待执行，1：已经执行

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getStartName() {
		return startName;
	}

	public void setStartName(String startName) {
		this.startName = startName;
	}

	public String getEndName() {
		return endName;
	}

	public void setEndName(String endName) {
		this.endName = endName;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	

}
