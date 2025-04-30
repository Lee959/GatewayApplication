GatewayApplication
实验目的
•	熟悉列表控件listview使用
•	认识网关和设备的组网模式
•	认识物联网的整体框架
实验过程
1.	启动应用，点击对应位置的网关
2.	进入网关下查看所有联网设备
3.	查看离线和在线状态
拓展
•	设备管理类DeviceMessagesManager的内部结构及封装
功能接口
获取网关列表
方法
QueryGatewayList
参数
参数	类型	描述
pageno	int	页数
pagesize	int	每页返回数量
返回值
bean 强转 GatewayListBean
属性	类型	描述
name	String	网关名
