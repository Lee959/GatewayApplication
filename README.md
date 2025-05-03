# GatewayApplication
#### 实验目的
•	熟悉列表控件listview使用
•	认识网关和设备的组网模式
•	认识物联网的整体框架
#### 实验过程
1.	启动应用，点击对应位置的网关
2.	进入网关下查看所有联网设备
3.	查看离线和在线状态
拓展
•	设备管理类DeviceMessagesManager的内部结构及封装

#### 核心类说明
| 类路径                                        | 描述                              |
| ------------------------------------------ | ------------------------------- |
| `owon/sdk/util/DeviceMessagesManager.java` | 设备操作类，封装灯光、电机、传感器等设备的控制与查询方法    |
| `owon/sdk/util/SocketMessageListener.java` | 数据回调类，包含登录结果、设备操作结果、设备状态获取等回调方法 |

###### 设备数据回调方法
类名： SocketMessageListener.java
方法： getMessage
| 字段名         | 类型    | 描述                 |
| ----------- | ----- | ------------------ |
| `commandID` | int   | 数据回调码（对应回调类型常量）    |
| `bean`      | class | 数据返回基类，需强转为对应设备的子类 |


#### 功能接口

###### 获取网关列表
方法： QueryGatewayList

| 参数名      | 类型  | 描述     |
| -------- | --- | ------ |
| pageno   | int | 页数     |
| pagesize | int | 每页返回数量 |

返回值
bean 强转 GatewayListBean
| 属性名  | 类型     | 描述  |
| ---- | ------ | --- |
| name | String | 网关名 |


###### 获取设备列表
方法： GetEpList
| 属性名        | 类型      | 描述    |
| ---------- | ------- | ----- |
| deviceType | int     | 设备类型码 |
| name       | String  | 设备名称  |
| linkStatus | boolean | 是否在线  |

###### 获取设备状态
方法： getDeviceState
| 参数名      | 类型    | 描述                     |
| -------- | ----- | ---------------------- |
| infobean | class | \[设备实体]\(# deviceCode) |
| cache    | int   | 0 不使用缓存值，1 使用缓存值       |
返回值： 详见设备回调说明


#### 数据回调码
回调方法： getMessage
| 属性                                    | 描述           |
| ------------------------------------- | ------------ |
| `Constants.UpdateEPList`              | 获取网关列表       |
| `Constants.ZigBeeGetEPList`           | 获取设备列表       |
| `Constants.SmartLightSetupSwitchgear` | 设置灯光结果       |
| `Constants.UpdateSwitchgear`          | 查询或控制灯后的状态回调 |
| `Constants.UpdateLight`               | 物理控制灯后的状态回调  |
| `Constants.THI_UPDATE`                | 温湿度传感器数据回调   |
| `Constants.ILLUM_UPDATE`              | 光照传感器数据回调    |
| `Constants.MotionSensorUpdate`        | 人体传感器查询状态回调  |
| `Constants.MotionSensor`              | 人体传感触发数据回调   |
| `Constants.WarningSensor`             | 烟雾监测数据回调     |

设备类型
| 属性                                            | 描述        |
| --------------------------------------------- | --------- |
| `Constants.LIGHT_601`                         | 灯，只有开关    |
| `Constants.LIGHT_EXTEND_LO_COLOR_TEMP_GOODVB` | 可调节亮度和色温灯 |
| `DeviceTypeCode.TH_SENSOR`                    | 温湿度传感器    |
| `DeviceTypeCode.LX_SENSOR`                    | 光照传感器     |
| `DeviceTypeCode.SMOKE_SENSOR_ZONE`            | 烟雾报警器     |
| `DeviceTypeCode.MOTION_SENSOR_ZONE`           | 人体传感器     |
| `DeviceTypeCode.AC_SENSOR`                    | 红外转发器     |
| `DeviceTypeCode.WARN_SENSOR`                  | 声光报警器     |
| `DeviceTypeCode.WARN_MOTOR`                   | 窗帘电机      |
| `DeviceTypeCode.DOOR_SENSOR`                  | 门磁感应器     |

