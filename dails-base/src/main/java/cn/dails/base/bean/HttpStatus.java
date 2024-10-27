package cn.dails.base.bean;//package cn.dails.base.bean;
//
//
//import org.springframework.http.HttpStatusCode;
//import org.springframework.lang.Nullable;
//
//public enum HttpStatus implements HttpStatusCode {
//    CONTINUE(100, org.springframework.http.HttpStatus.Series.INFORMATIONAL, "Continue"),
//    SWITCHING_PROTOCOLS(101, org.springframework.http.HttpStatus.Series.INFORMATIONAL, "Switching Protocols"),
//    PROCESSING(102, org.springframework.http.HttpStatus.Series.INFORMATIONAL, "Processing"),
//    EARLY_HINTS(103, org.springframework.http.HttpStatus.Series.INFORMATIONAL, "Early Hints"),
//    /** @deprecated */
//    @Deprecated(
//            since = "6.0.5"
//    )
//    CHECKPOINT(103, org.springframework.http.HttpStatus.Series.INFORMATIONAL, "Checkpoint"),
//    OK(200, org.springframework.http.HttpStatus.Series.SUCCESSFUL, "OK"),
//    CREATED(201, org.springframework.http.HttpStatus.Series.SUCCESSFUL, "Created"),
//    ACCEPTED(202, org.springframework.http.HttpStatus.Series.SUCCESSFUL, "Accepted"),
//    NON_AUTHORITATIVE_INFORMATION(203, org.springframework.http.HttpStatus.Series.SUCCESSFUL, "Non-Authoritative Information"),
//    NO_CONTENT(204, org.springframework.http.HttpStatus.Series.SUCCESSFUL, "No Content"),
//    RESET_CONTENT(205, org.springframework.http.HttpStatus.Series.SUCCESSFUL, "Reset Content"),
//    PARTIAL_CONTENT(206, org.springframework.http.HttpStatus.Series.SUCCESSFUL, "Partial Content"),
//    MULTI_STATUS(207, org.springframework.http.HttpStatus.Series.SUCCESSFUL, "Multi-Status"),
//    ALREADY_REPORTED(208, org.springframework.http.HttpStatus.Series.SUCCESSFUL, "Already Reported"),
//    IM_USED(226, org.springframework.http.HttpStatus.Series.SUCCESSFUL, "IM Used"),
//    MULTIPLE_CHOICES(300, org.springframework.http.HttpStatus.Series.REDIRECTION, "Multiple Choices"),
//    MOVED_PERMANENTLY(301, org.springframework.http.HttpStatus.Series.REDIRECTION, "Moved Permanently"),
//    FOUND(302, org.springframework.http.HttpStatus.Series.REDIRECTION, "Found"),
//    /** @deprecated */
//    @Deprecated
//    MOVED_TEMPORARILY(302, org.springframework.http.HttpStatus.Series.REDIRECTION, "Moved Temporarily"),
//    SEE_OTHER(303, org.springframework.http.HttpStatus.Series.REDIRECTION, "See Other"),
//    NOT_MODIFIED(304, org.springframework.http.HttpStatus.Series.REDIRECTION, "Not Modified"),
//    /** @deprecated */
//    @Deprecated
//    USE_PROXY(305, org.springframework.http.HttpStatus.Series.REDIRECTION, "Use Proxy"),
//    TEMPORARY_REDIRECT(307, org.springframework.http.HttpStatus.Series.REDIRECTION, "Temporary Redirect"),
//    PERMANENT_REDIRECT(308, org.springframework.http.HttpStatus.Series.REDIRECTION, "Permanent Redirect"),
//    BAD_REQUEST(400, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Bad Request"),
//    UNAUTHORIZED(401, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Unauthorized"),
//    PAYMENT_REQUIRED(402, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Payment Required"),
//    FORBIDDEN(403, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Forbidden"),
//    NOT_FOUND(404, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Not Found"),
//    METHOD_NOT_ALLOWED(405, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Method Not Allowed"),
//    NOT_ACCEPTABLE(406, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Not Acceptable"),
//    PROXY_AUTHENTICATION_REQUIRED(407, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Proxy Authentication Required"),
//    REQUEST_TIMEOUT(408, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Request Timeout"),
//    CONFLICT(409, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Conflict"),
//    GONE(410, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Gone"),
//    LENGTH_REQUIRED(411, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Length Required"),
//    PRECONDITION_FAILED(412, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Precondition Failed"),
//    PAYLOAD_TOO_LARGE(413, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Payload Too Large"),
//    /** @deprecated */
//    @Deprecated
//    REQUEST_ENTITY_TOO_LARGE(413, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Request Entity Too Large"),
//    URI_TOO_LONG(414, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "URI Too Long"),
//    /** @deprecated */
//    @Deprecated
//    REQUEST_URI_TOO_LONG(414, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Request-URI Too Long"),
//    UNSUPPORTED_MEDIA_TYPE(415, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Unsupported Media Type"),
//    REQUESTED_RANGE_NOT_SATISFIABLE(416, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Requested range not satisfiable"),
//    EXPECTATION_FAILED(417, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Expectation Failed"),
//    I_AM_A_TEAPOT(418, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "I'm a teapot"),
//    /** @deprecated */
//    @Deprecated
//    INSUFFICIENT_SPACE_ON_RESOURCE(419, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Insufficient Space On Resource"),
//    /** @deprecated */
//    @Deprecated
//    METHOD_FAILURE(420, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Method Failure"),
//    /** @deprecated */
//    @Deprecated
//    DESTINATION_LOCKED(421, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Destination Locked"),
//    UNPROCESSABLE_ENTITY(422, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Unprocessable Entity"),
//    LOCKED(423, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Locked"),
//    FAILED_DEPENDENCY(424, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Failed Dependency"),
//    TOO_EARLY(425, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Too Early"),
//    UPGRADE_REQUIRED(426, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Upgrade Required"),
//    PRECONDITION_REQUIRED(428, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Precondition Required"),
//    TOO_MANY_REQUESTS(429, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Too Many Requests"),
//    REQUEST_HEADER_FIELDS_TOO_LARGE(431, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Request Header Fields Too Large"),
//    UNAVAILABLE_FOR_LEGAL_REASONS(451, org.springframework.http.HttpStatus.Series.CLIENT_ERROR, "Unavailable For Legal Reasons"),
//    INTERNAL_SERVER_ERROR(500, org.springframework.http.HttpStatus.Series.SERVER_ERROR, "Internal Server Error"),
//    NOT_IMPLEMENTED(501, org.springframework.http.HttpStatus.Series.SERVER_ERROR, "Not Implemented"),
//    BAD_GATEWAY(502, org.springframework.http.HttpStatus.Series.SERVER_ERROR, "Bad Gateway"),
//    SERVICE_UNAVAILABLE(503, org.springframework.http.HttpStatus.Series.SERVER_ERROR, "Service Unavailable"),
//    GATEWAY_TIMEOUT(504, org.springframework.http.HttpStatus.Series.SERVER_ERROR, "Gateway Timeout"),
//    HTTP_VERSION_NOT_SUPPORTED(505, org.springframework.http.HttpStatus.Series.SERVER_ERROR, "HTTP Version not supported"),
//    VARIANT_ALSO_NEGOTIATES(506, org.springframework.http.HttpStatus.Series.SERVER_ERROR, "Variant Also Negotiates"),
//    INSUFFICIENT_STORAGE(507, org.springframework.http.HttpStatus.Series.SERVER_ERROR, "Insufficient Storage"),
//    LOOP_DETECTED(508, org.springframework.http.HttpStatus.Series.SERVER_ERROR, "Loop Detected"),
//    BANDWIDTH_LIMIT_EXCEEDED(509, org.springframework.http.HttpStatus.Series.SERVER_ERROR, "Bandwidth Limit Exceeded"),
//    NOT_EXTENDED(510, org.springframework.http.HttpStatus.Series.SERVER_ERROR, "Not Extended"),
//    NETWORK_AUTHENTICATION_REQUIRED(511, org.springframework.http.HttpStatus.Series.SERVER_ERROR, "Network Authentication Required");
//
//    private static final org.springframework.http.HttpStatus[] VALUES = values();
//    private final int value;
//    private final org.springframework.http.HttpStatus.Series series;
//    private final String reasonPhrase;
//
//    private HttpStatus(int value, org.springframework.http.HttpStatus.Series series, String reasonPhrase) {
//        this.value = value;
//        this.series = series;
//        this.reasonPhrase = reasonPhrase;
//    }
//
//    public int value() {
//        return this.value;
//    }
//
//    public org.springframework.http.HttpStatus.Series series() {
//        return this.series;
//    }
//
//    public String getReasonPhrase() {
//        return this.reasonPhrase;
//    }
//
//    public boolean is1xxInformational() {
//        return this.series() == org.springframework.http.HttpStatus.Series.INFORMATIONAL;
//    }
//
//    public boolean is2xxSuccessful() {
//        return this.series() == org.springframework.http.HttpStatus.Series.SUCCESSFUL;
//    }
//
//    public boolean is3xxRedirection() {
//        return this.series() == org.springframework.http.HttpStatus.Series.REDIRECTION;
//    }
//
//    public boolean is4xxClientError() {
//        return this.series() == org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
//    }
//
//    public boolean is5xxServerError() {
//        return this.series() == org.springframework.http.HttpStatus.Series.SERVER_ERROR;
//    }
//
//    public boolean isError() {
//        return this.is4xxClientError() || this.is5xxServerError();
//    }
//
//    public String toString() {
//        int var10000 = this.value;
//        return "" + var10000 + " " + this.name();
//    }
//
//    public static org.springframework.http.HttpStatus valueOf(int statusCode) {
//        org.springframework.http.HttpStatus status = resolve(statusCode);
//        if (status == null) {
//            throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
//        } else {
//            return status;
//        }
//    }
//
//    @Nullable
//    public static org.springframework.http.HttpStatus resolve(int statusCode) {
//        org.springframework.http.HttpStatus[] var1 = VALUES;
//        int var2 = var1.length;
//
//        for(int var3 = 0; var3 < var2; ++var3) {
//            org.springframework.http.HttpStatus status = var1[var3];
//            if (status.value == statusCode) {
//                return status;
//            }
//        }
//
//        return null;
//    }
//
//    public static enum Series {
//        INFORMATIONAL(1),
//        SUCCESSFUL(2),
//        REDIRECTION(3),
//        CLIENT_ERROR(4),
//        SERVER_ERROR(5);
//
//        private final int value;
//
//        private Series(int value) {
//            this.value = value;
//        }
//
//        public int value() {
//            return this.value;
//        }
//
//        /** @deprecated */
//        @Deprecated
//        public static org.springframework.http.HttpStatus.Series valueOf(org.springframework.http.HttpStatus status) {
//            return status.series;
//        }
//
//        public static org.springframework.http.HttpStatus.Series valueOf(int statusCode) {
//            org.springframework.http.HttpStatus.Series series = resolve(statusCode);
//            if (series == null) {
//                throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
//            } else {
//                return series;
//            }
//        }
//
//        @Nullable
//        public static org.springframework.http.HttpStatus.Series resolve(int statusCode) {
//            int seriesCode = statusCode / 100;
//            org.springframework.http.HttpStatus.Series[] var2 = values();
//            int var3 = var2.length;
//
//            for(int var4 = 0; var4 < var3; ++var4) {
//                org.springframework.http.HttpStatus.Series series = var2[var4];
//                if (series.value == seriesCode) {
//                    return series;
//                }
//            }
//
//            return null;
//        }
//    }
//}