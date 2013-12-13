package mt.manager;

public interface Manager {

	/**
	 * 保存当前页面数据，把数据输出到文件中。
	 * 在返回上一次菜单时，会被调用。
	 */
	void flushData();
}
