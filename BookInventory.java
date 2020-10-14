
public class BookInventory {
	public Book book;
	public Integer currentQuantity;
	public Integer stockQuantity;
	
	public BookInventory(Book book, Integer currentQuantity, Integer stockQuantity) {
		this.book = book;
		this.currentQuantity = currentQuantity;
		this.stockQuantity = stockQuantity;
	}
}
