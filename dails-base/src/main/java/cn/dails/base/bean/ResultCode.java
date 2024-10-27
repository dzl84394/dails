package cn.dails.base.bean;


public enum ResultCode {
//1，ACCEPTED 接受，但是可能不知道最终结果不知道状态
//2 SUCCESS 成功
//4, FAILED 拒绝 鉴权或参数不对，遇到异常 缺少参数，服务端问题，未找到对象、对象已存在，幂等拒绝异常了
    ACCEPTED(3000,Series.ACCEPTED, "Early Hints"),
    SUCCESS(200,Series.SUCCESSFUL, "OK"),
//    REJECTED(4000,Series.REJECTED, "REJECTED"),//拒绝
    FAILED(5000,Series.FAILED, "Server Error"),//失败
    FAILED5002(5002,Series.FAILED, "Server Error");
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


        private final int value;

        private Series(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }
}
