package in.co.rays.project_3.dto;

public class StockDTO extends BaseDTO {

	private Long stockId;
	private String stockName;
	private Long price;
	private Integer quantity;

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String getKey() {
		return stockId + "";
	}

	@Override
	public String getValue() {
		return stockName;
	}
}