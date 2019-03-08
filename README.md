[TOC]



# 第三方支付开发推演

## 逻辑推演

初步个人认为，对接第三方支付平台，不需要太担心文档的事，然后事与愿违；

1. 支付宝支付文档相对内容会比较简单和直接
2. 微信支付文档就显示比较多，原因也很好理解，毕竟微信的线上线下场景还是比较多的，和自身IM的也有关，10 亿的日活用户，多多少少还是比传统中的第三方支付要复杂的多
3. 支付的核心功能永远是，如何保证支付操作的安全保障，支付平台提供的 API 和自身业务的结合，支付api参数属性的记录测试
4. 两者支付平台的流程都是，沙箱环境-线上环境

## [蚂蚁金服开放平台](https://openhome.alipay.com/platform/home.htm)

### 文档中心

* 开发文档 https://docs.open.alipay.com/catalog
* API 文档 https://docs.open.alipay.com/api
* 业务中心 https://open.alipay.com/productDocument.htm
* 规则中心 https://docs.open.alipay.com/rules

### [沙箱环境](https://openhome.alipay.com/platform/appDaily.htm?tab=info)

* 蚂蚁金开放平台-开发中心-开发服务-研发服务-沙箱

* 选择要调试的产品功能，根据文档进行开发；下列提供的功能都已经开通权限，无需签约

#### 沙箱应用

```
APPID
支付宝网关
应用公钥
应用私钥
支付宝公钥
```



#### 沙箱账号

```
# 商家账号
商家账号ufbxxxxxx9@sandbox.com
商户UID208810xxxxxx284
登录密码111111
账户余额
0.00充值

# 买家信息
买家账号xyyxxxxxx2@sandbox.com
登录密码111111
支付密码111111
用户名称沙箱环境
证件类型身份证(IDENTITY_CARD)
证件号码6711841xxxxxx16553
账户余额
99999.00充值
```

#### 沙箱工具

* 沙箱钱包只支持扫一扫、付款码、门店详情页功能，其余功能不提供~
* 下载 Android 版本

#### 当面付 DMEO

* https://docs.open.alipay.com/54/104506/

* F2FPay_Demo_Java.zip

  ```
  TradePayDemo
  TradePaySDK
  .gitignore
  readme.txt
  当面付调用流程说明文档(java).docx
  ```

  

  ```java
  Main.class
      
  public static void main(String[] args) {
          Main main = new Main();
  
          // 系统商商测试交易保障接口api
          //        main.test_monitor_sys();
  
          // POS厂商测试交易保障接口api
          //        main.test_monitor_pos();
  
          // 测试交易保障接口调度
          //        main.test_monitor_schedule_logic();
  
          // 测试当面付2.0支付（使用未集成交易保障接口的当面付2.0服务）
          //        main.test_trade_pay(tradeService);
  
          // 测试查询当面付2.0交易
                  main.test_trade_query();
  
          // 测试当面付2.0退货
          //        main.test_trade_refund();
  
          // 测试当面付2.0生成支付二维码
          // main.test_trade_precreate();
   }
  ```

  

  ```properties
  
  # 支付宝网关名、partnerId(商户 UID)和appId
  open_api_domain = https://openapi.alipaydev.com/gateway.do（沙箱环境）
  open_api_domain = https://openapi.alipay.com/gateway.do(正式环境)
  mcloud_api_domain = http://mcloudmonitor.com/gateway.do
  pid = 2088102177085940
  # APPID
  appid = 2016092400586843
  
  # RSA私钥、公钥和支付宝公钥
  private_key = 
  public_key = 
  
  #SHA1withRsa对应支付宝公钥
  #alipay_public_key = 
  
  #SHA256withRsa对应支付宝公钥
  alipay_public_key = 
  
  # 签名类型: RSA->SHA1withRsa,RSA2->SHA256withRsa
  sign_type = RSA2
  # 当面付最大查询次数和查询间隔（毫秒）
  max_query_retry = 5
  query_duration = 5000
  
  # 当面付最大撤销次数和撤销间隔（毫秒）
  max_cancel_retry = 3
  cancel_duration = 2000
  
  # 交易保障线程第一次调度延迟和调度间隔（秒）
  heartbeat_delay = 5
  heartbeat_duration = 900
  
  ```

  



### [正式环境](https://openhome.alipay.com/platform/developerIndex.htm)

#### 创建应用

* 配置应用环境

* 应用申请上线
  * 应用网关(支付宝主动通知的 url 地址，一般是指商户信息变更等...)

  * 授权回调地址(检测应用服务器情况，必填)



#### 应用开发中

* 一个工作日，快的话几个小时

#### 应用上线中

#### 应用已上线

##### 功能列表(签约)

* APP 支付
* 当面付(签约，好像也需要一个工作日，快的话几个小时)
* 手机网站支付
  * 费率按单笔计算；
  * 一般行业费率：0.6%；自2018年5月9日起，特殊行业新签约费率从1.2%调整为1%，特殊行业范围包括：休闲游戏；网络游戏点卡、渠道代理；游戏系统商；网游周边服务、交易平台；网游运营商（含网页游戏）。





#### 实际开发中的一些记录

```markdown
### 商户平台相关信息
支付宝企业账号：xxxxxx@tysd.net.cn
密码: 		jxxxxxx8
PID		(https://openhome.alipay.com/platform/accountSetting.htm)
APPID 	(https://openhome.alipay.com/platform/keyManage.htm)
开发设置		 (https://openhome.alipay.com/platform/appManage.htm#/app/2019022763426296/appInfo)


### invalid-app-id（无效的AppID）参数问题自查方案
1.首先检查支付宝网关
沙箱环境网关为： https://openapi.alipaydev.com/gateway.do 
正式环境网关为： https://openapi.alipay.com/gateway.do 
2.appid不存在，查看地址

### ISV权限不足，建议在开发者中心检查签约是否已经生效
发现功能没有签约
* APP支付
* 当面付
	* 不需要收取手续费，审核需要一个工作日
* 手机网站支付
	* 服务费按照单笔交易金额的【0.6%】收取
	
### 卖家不在设置的收款账户列表之中
sellerId(收款账号)
https://docs.open.alipay.com/205/103873

### 关联账号（子账户必须也是商户账号）
需要超级管理员的支付密码（敏感）
https://certify.alipay.com/relativeChild.htm
添加账户一个人名下最多允许拥有11个支付宝账户
你输入的认证身份信息与当前账户的身份信息不一致。


### 添加收款账户
口碑开店设置/修改门店收款账号(https://docs.open.alipay.com/205/103873)
约束：您的收款账户须与您的签约支付宝账户同名（即属于同一身份证/营业执照名下的支付宝账户）。
* 二维码预支付生成后，二维码废弃
* 继续扫码，二维码已失效，请商家更换二维码后重新支付
```









## [微信支付商户平台](https://pay.weixin.qq.com/index.php/core/home/login?return_url=%2F)

### [开发文档](https://pay.weixin.qq.com/wiki/doc/api/index.html)

* JSAPI支付

* Native支付
  * Native支付可分为两种模式，商户根据支付场景选择相应模式。
    * 模式二与模式一相比，流程更为简单，不依赖设置的回调支付URL。商户后台系统先调用微信支付的统一下单接口，微信后台系统返回链接参数code_url，商户后台系统将code_url值生成二维码图片，用户使用微信客户端扫码后发起支付。注意：code_url有效期为2小时，过期后扫码不能再发起支付。
    * 模式二与模式一相比，流程更为简单，不依赖设置的回调支付URL。商户后台系统先调用微信支付的统一下单接口，微信后台系统返回链接参数code_url，商户后台系统将code_url值生成二维码图片，用户使用微信客户端扫码后发起支付。注意：code_url有效期为2小时，过期后扫码不能再发起支付。
* APP支付

### 沙箱环境

* 账户中心-账户设置

  * 添加员工账号(防止超管账户登录，一直扫码)
  * API 安全
    * API 证书
      * API 证书 一年能设置 ? 次，好像是三次
      * API 证书主要用到了退款功能
    * API 密钥
      * 好像没用到，我也奇怪

* 最佳实践

  * [支付验收指引](https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=23_1)

    * 验收说明

      为保证商户接入质量，提升交易安全及用户体验，微信支付的合作服务商在正式上线交易前，必须先根据本文指引完成验收。验收完成后，服务商在验收公众平台（微信号：WXPayAssist）提交验收通过申请，审核通过后，才能开通相应的支付权限（如：付款码支付）。否则，请根据审核驳回提示，重新完成验收。

    * 仿真测试系统

      * 说白了就是沙箱环境，相对于蚂蚁金服沙箱来说，这个更强制性，所有的配置都是商户线上的配置信息，唯一的区别是在 url + sandboxnew

    

  ```markdown
  ### 商户平台相关信息
  微信公众号登陆
  账号：	xxxxxx@tysd.net.cn
  密码：	jxxxxxx8
  
  商户平台登录账号
  账号：1xxxxxx1@1526072231
  密码：HxxxxxxJ@163.com
  
  1. 分配员工账号
  2. 产品中心(申请 native 支付功能)
  
  超级管理员
  188xxxxxx01
  扫码登录
  bug 
  其他错误，请重试
  错误代码：-206
  
  商户号：152xxxxxx31
  预留手机号：188xxxxxx701
  
  【客户处理信息填充】
  商户号 15xxxxxx31
  结算账户 xxxxxxx2503
  联系手机 130xxxxxx393
  商户平台账户登录的时候，出现其他错误，请重试
  
  
  管理员
  登录账号：xixxxxxxo@1526072231
  操作密码：xtxxxxxx20	
  员工微信号：xiaoxxxxxx73107
  联系方式：15xxxxxx93
  联系邮箱方式：xxxxxx@tysd.net.cn
  
  
  【商户审核通过】
  微信支付商户号(mch_id) 15xxxxxx31
  商户平台登录帐号 152xxxxxx072231
  API密钥：a409badcexxxxxxa270591776b
  证书：1526072231_20190228_cert.zip
  【微信公众号】
  AppID wxa2f8xxxxxxa41
  AppSecret  002be24xxxxxx8f7fda89273fa35f
  邮箱：xxxxxx@tysd.net.cn  密码：xxxxxx68
  
  
  
  
  ### Native 支付可分为两种模式，商户根据支付场景选择相应的模式
  
  【模式一】：商户后台系统根据微信支付规则链接生成二维码，链接中带固定参数productid（可定义为产品标识或订单号）。用户扫码后，微信支付系统将productid和用户唯一标识(openid)回调商户后台系统(需要设置支付回调URL)，商户后台系统根据productid生成支付交易，最后微信支付系统发起用户支付流程。
  
  【模式二】：商户后台系统调用微信支付【统一下单API】生成预付交易，将接口返回的链接生成二维码，用户扫码后输入密码完成支付交易。注意：该模式的预付单有效期为2小时，过期后无法支付。详细接入步骤：模式二
  
  
  ### 支付验收指引（sandboxnew）
  步骤1
  商户通过审核，收到审核通过的邮件。邮件中包含了商户的MCHID、APPID和密码等开发者信息。商户使用MCHID、SIGN从后台API获取验签环境密钥。
  
  步骤2
  测试硬件全部到位，且被测的APP已被成功安装在硬件。
  4、扫码支付：联网PC一台，且网站已接入微信支付功能；手机1台，已安装微信
  
  步骤3
  关注公众号“微信支付商户接入验收助手”（ 微信号： WXPayAssist），在验收case栏，选择本次接入的支付类型，如付款码支付，即可看到全部验收用例。
  
  步骤4
  修改代码或配置中所有微信支付api的链接，对接仿真系统。
  
  步骤5
  严格按照用例的顺序、金额执行用例，确保用例的检查点完全符合预期。
  
  步骤6
  全部用例测试通过后，商户在公众号“我的验收”里提交验收通过申请。微信支付团队将在3个工作日内完成验收审核。
  
  ### 生成二维码(预支付模式)
  weixin：//wxpay/bizpayurl?sr=XXXXX
  
  ### 构建参数
  终端IP：支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
  117.44.189.120
  通知地址（notify_url）：异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
  http://pay.fangxie365.com/wx-pay/notifyUrl
  
  ### FAIL
  返回代码：[FAIL]，返回信息：[沙箱验证签名失败，请确认沙箱签名key是否正确（通过getsignkey调用生成）]，微信返回的原始报文：
  wxService.getSandboxSignKey()
  
  Q1:返回代码：[FAIL]，返回信息：[获取沙箱密钥失败，确认交易密钥是否正确]，微信返回的原始报文：
  A1：config.setMchKey(wxService.getSandboxSignKey());
  Q2:返回代码：[FAIL]，返回信息：[沙箱支付金额(1)无效，请检查需要验收的case]，微信返回的原始报文：
  A2:用例1【扫码-正常】订单金额3.01元，用户支付成功
  二维码链接: "codeUrl": "weixin://wxpay/s/An4baqw"
  <prepay_id><![CDATA[wx20190304134929944373]]></prepay_id>
  <code_url><![CDATA[weixin://wxpay/s/An4baqw]]></code_url>
  码已过期，请重新生成
  
  
  ### [证书](https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3)
  微信支付接口中，涉及资金回滚的接口会使用到API证书，包括退款、撤销接口。商家在申请微信支付成功后，收到的相应邮件后，可以按照指引下载API证书，也可以按照以下路径下载：微信商户平台(pay.weixin.qq.com)-->账户中心-->账户设置-->API安全 。证书文件说明如下：
  https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3
  (API证书与秘钥) http://kf.qq.com/faq/180824JvUZ3i180824YvMNJj.html
  
  
  
  ### [更换证书](https://pay.weixin.qq.com/index.php/core/cert/api_cert)
  商户号：xxx
  商户名称:xxx
  复制请求串：
  复制证书串
  
  
  
  ### 支付模块拆分
  abd-payment-common
  * 封装支付相关的input,output参数，减少支付内部服务参数的暴露
  abd-payment-server
  * TypeHandler
  abd-payment-server-api
  
  ```

  

  

  

### 正式环境

1. 退款的时候，证书有误问题，由于在编译过程中，p12证书被改变了，导致证书错误

   ```xml
   <!-- 是由于在编译过程中，p12证书被改变了 -->
   <plugin>
       <groupId>org.apache.maven.plugins</groupId>
       <artifactId>maven-resources-plugin</artifactId>
       <configuration>
           <nonFilteredFileExtensions>
               <nonFilteredFileExtension>pem</nonFilteredFileExtension>
               <nonFilteredFileExtension>pfx</nonFilteredFileExtension>
               <nonFilteredFileExtension>p12</nonFilteredFileExtension>
           </nonFilteredFileExtensions>
       </configuration>
   </plugin>
   ```

* 支付模块拆分

  ```markdown
  # abd-payment-common
  * 也应该由 payment-server 提供，payment-server 需要的 output,input
  # abd-payment-server
  # abd-payment-server-api
  * 这个服务应该由 payment-server 本身提供，由于需要识别对应的 instance-name, url
  ；所以需要服务提供者去配置开发，暴露出来，供其他服务使用 
  ```

* Feign

  ```java
  @GetMapping("/trade/refund/{outTradeNo}")
      public ResultBody tradeRefund(@PathVariable(value = "outTradeNo") String outTradeNo);
  
  @GetMapping("/trade/refund/{outTradeNo}")
      public ResultBody tradeRefund(@requestParam(value = "outTradeNo") String outTradeNo);
  
  涉及到 @PathVariable @requestParam 两个注解，需要写明 value or name propreties
  ```

  







## Wechat-Group/WxJava

## 分模块开发文档

1. [微信公众号(mp)开发文档（包含网页授权登录相关接口）](https://github.com/Wechat-Group/weixin-java-tools/wiki/%E5%85%AC%E4%BC%97%E5%8F%B7%E5%BC%80%E5%8F%91%E6%96%87%E6%A1%A3)
2. [微信企业号/企业微信(cp)开发文档](https://github.com/Wechat-Group/weixin-java-tools/wiki/%E4%BC%81%E4%B8%9A%E5%8F%B7%E5%BC%80%E5%8F%91%E6%96%87%E6%A1%A3)
3. [微信支付(pay)开发文档](https://github.com/Wechat-Group/weixin-java-tools/wiki/%E5%BE%AE%E4%BF%A1%E6%94%AF%E4%BB%98%E5%BC%80%E5%8F%91%E6%96%87%E6%A1%A3)
4. [微信开放平台(open)开发文档](https://github.com/Wechat-Group/weixin-java-tools/wiki/%E5%BE%AE%E4%BF%A1%E5%BC%80%E6%94%BE%E5%B9%B3%E5%8F%B0%E5%BC%80%E5%8F%91%E6%96%87%E6%A1%A3)
5. [微信小程序(miniapp)开发文档](https://github.com/Wechat-Group/weixin-java-tools/wiki/%E5%B0%8F%E7%A8%8B%E5%BA%8F%E5%BC%80%E5%8F%91%E6%96%87%E6%A1%A3)



## 微信支付开发

1. [微信支付官方文档入口](https://pay.weixin.qq.com/wiki/doc/api/index.html)
2. [微信支付Demo项目](https://github.com/binarywang/weixin-java-pay-demo)
3. [微信支付使用说明](https://github.com/Wechat-Group/weixin-java-tools/wiki/%E5%BE%AE%E4%BF%A1%E6%94%AF%E4%BB%98)
4. [企业付款相关接口的Javadoc](https://binarywang.github.io/weixin-java-pay-javadoc/com/github/binarywang/wxpay/service/EntPayService.html)
5. [其他已实现的微信支付接口的Javadoc](https://binarywang.github.io/weixin-java-pay-javadoc/com/github/binarywang/wxpay/service/WxPayService.html)
6. 微信支付接口请求参数正确情况下，如果一直报签名不对，请尝试修改下商户API密钥。
7. 微信支付退款或发红包请求时报“DerInputStream.getLength(): lengthTag=111, too big.”，请参考[此文](https://blog.csdn.net/oneniu/article/details/70154197)，或者[此文](
