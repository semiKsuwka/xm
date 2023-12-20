package xm.ui.example.entities;

public class Stock {
    private String symbolWithDescription;
    private String symbol;
    private String minSpread;
    private String minMaxTradeSize;
    private String marginRequirement;
    private String swapLong;

    public String getSwapShort() {
        return swapShort;
    }

    public void setSwapShort(String swapShort) {
        this.swapShort = swapShort;
    }

    private String swapShort;
    private String limitStopLevel;

    public String getSymbolWithDescription() {
        return symbolWithDescription;
    }

    public void setSymbolWithDescription(String symbolWithDescription) {
        this.symbolWithDescription = symbolWithDescription;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getMinSpread() {
        return minSpread;
    }

    public void setMinSpread(String minSpread) {
        this.minSpread = minSpread;
    }

    public String getMinMaxTradeSize() {
        return minMaxTradeSize;
    }

    public void setMinMaxTradeSize(String minMaxTradeSize) {
        this.minMaxTradeSize = minMaxTradeSize;
    }

    public String getMarginRequirement() {
        return marginRequirement;
    }

    public void setMarginRequirement(String marginRequirement) {
        this.marginRequirement = marginRequirement;
    }

    public String getSwapLong() {
        return swapLong;
    }

    public void setSwapLong(String swapLong) {
        this.swapLong = swapLong;
    }

    public String getLimitStopLevel() {
        return limitStopLevel;
    }

    public void setLimitStopLevel(String limitStopLevel) {
        this.limitStopLevel = limitStopLevel;
    }


}
