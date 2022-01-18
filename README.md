# ShortPayload

极致缩小`Java`反序列化`Payload`

## 效果

注意：这里的长度是指反序列化`Payload`进行`Base64`编码后的长度

|       反序列化链       | YSOSERIAL长度 | 缩小后长度 |  缩小率  |
|:-----------------:|:-----------:|:-----:|:-----:|
| CommonsBeanutils1 |    3692     | 1296  | 64.8% |
| CommonsCollections1 |
| CommonsCollections6 | 1708 | 1560 | 8.6% |