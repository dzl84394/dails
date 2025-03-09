package cn.dails.base.bean;


public enum ResultCode {
//1，ACCEPTED 接受，但是可能不知道最终结果不知道状态
//2 SUCCESS 成功
//4, FAILED 拒绝 鉴权或参数不对，遇到异常 缺少参数，服务端问题，未找到对象、对象已存在，幂等拒绝异常了

    SUCCESS(200,Series.SUCCESSFUL, "OK"),
    ACCEPTED(3000,Series.ACCEPTED, "Early Hints"),
//    REJECTED(4000,Series.REJECTED, "REJECTED"),//拒绝
    FAILED(5000,Series.FAILED, "Server Error"),//失败
    PARAM_MISSING(5001,Series.REJECTED,"参数错误：缺少必要参数"),
    PARAM_INVALID(5002,Series.REJECTED,"参数错误：参数格式不正确"),
    OBJECT_NOT_FOUND(5003, Series.FAILED, "对象不存在"),
    PERMISSION_DENIED(5004, Series.FAILED, "没有权限访问该对象"),
    BALANCE_INSUFFICIENT(5005, Series.FAILED, "余额不足"),
    STATUS_NOT_SUPPORTED(5006, Series.FAILED, "当前状态不支持此操作"),
    INTERNAL_ERROR(5101, Series.FAILED, "服务器内部错误，请稍后重试"),
    OVERFLOW_ERROR(5102, Series.FAILED, "服务限流，请稍后重试");
    ;

//    CONTINUE(100,HttpStatus.Series.INFORMATIONAL, "Continue"),
    private static final ResultCode[] VALUES = values();
    private final int value;
    private final Series series;
    private final String resultDesc;

    private ResultCode(int value, Series series, String resultDesc) {
        this.value = value;
        this.series = series;
        this.resultDesc = resultDesc;
    }

    public int value() {
        return this.value;
    }

    public Series series() {
        return this.series;
    }


    public String getResultDesc() {
        return resultDesc;
    }

    public String toString() {
        int var10000 = this.value;
        return "" + var10000 + " " + this.name();
    }

    public static ResultCode valueOf(int statusCode) {
        ResultCode status = resolve(statusCode);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
        } else {
            return status;
        }
    }
    public static ResultCode resolve(int statusCode) {
        ResultCode[] var1 = VALUES;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ResultCode status = var1[var3];
            if (status.value == statusCode) {
                return status;
            }
        }

        return null;
    }


    public static enum Series {
        SUCCESSFUL(2),//Successful（成功状态码），以2开头的状态码表示请求被成功接收、理解、处理并返回。
        ACCEPTED(3),//Redirection（重定向状态码）以3开头的状态码表示需要客户端采取进一步的操作才能完成请求。
        REJECTED(4),//Client Error（客户端错误状态码）以4开头的状态码表示客户端发送的请求中存在错误或无法完成请求。
        FAILED(5);//Server Error（服务器错误状态码）以5开头的状态码表示服务器在处理请求时发生了错误。

        //        INFORMATIONAL(1),请求已被接收，继续处理。
//        SUCCESSFUL(2),请求已成功处理。
//        REDIRECTION(3),需要进一步操作以完成请求。
//        CLIENT_ERROR(4),客户端请求有误。
//        SERVER_ERROR(5);服务器处理请求失败。

        // 入参格式错误或者缺少入参
        // id对应对象不存在或者没有权限
        // 操作的余额或者状态不支持下一步操作
        // 服务器内部错误（异常，溢出）
//
        private final int value;

        private Series(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }
}
