package Entity;

public class ViewAdminStatistics {
    private int currentBorrowReaderCount;
    private int todayBorrowCount;
    private int todayReturnCount;
    private int totalStock;

    public int getCurrentBorrowReaderCount() {
        return currentBorrowReaderCount;
    }

    public void setCurrentBorrowReaderCount(int currentBorrowReaderCount) {
        this.currentBorrowReaderCount = currentBorrowReaderCount;
    }

    public int getTodayBorrowCount() {
        return todayBorrowCount;
    }

    public void setTodayBorrowCount(int todayBorrowCount) {
        this.todayBorrowCount = todayBorrowCount;
    }

    public int getTodayReturnCount() {
        return todayReturnCount;
    }

    public void setTodayReturnCount(int todayReturnCount) {
        this.todayReturnCount = todayReturnCount;
    }

    public int getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }
}
