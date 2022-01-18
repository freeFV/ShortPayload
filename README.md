# ShortPayload

极致缩小`Java`反序列化`Payload`

使用：`java -jar ShortPayload.jar [gadget-name] [command]`

例如：`java -jar ShortPayload.jar CC6 "calc.exe"`

## 效果

注意：这里的长度是指反序列化`Payload`进行`Base64`编码后的长度

|        反序列化链         | YSOSERIAL长度 | 缩小后长度 |  缩小率  |
|:--------------------:|:-----------:|:-----:|:-----:|
|  CommonsBeanutils1   |    3692     | 1296  | 64.8% |
| CommonsCollections1  |    1868     | 1748  | 6.4%  |
| CommonsCollections2  |    4176     | 1708  | 41.4% |
| CommonsCollections3  |    4784     | 2444  | 48.9% |
| CommonsCollections4  |    4720     | 2256  | 52.2% |
| CommonsCollections5  |    2772     | 3044  | -8.9% |
| CommonsCollections6  |    1708     | 1560  | 8.6%  |
| CommonsCollections7  |    1700     | 1636  | 3.7%  |
| CommonsCollectionsK1 |    2464     | 1708  | 30.6% |
| CommonsCollectionsK2 |    2472     | 1716  | 30.5% |
| CommonsCollectionsK3 |    1644     | 1604  | 2.4%  |
| CommonsCollectionsK4 |    1652     | 1612  | 2.4%  |


