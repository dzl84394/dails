package cn.dails.base.bean;


public class PageVo {
    /**
     * 当前页数
     *
     */
    private long currentPage = 0;

    /**
     * 一页多少条
     */
    private long size = 10;

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}