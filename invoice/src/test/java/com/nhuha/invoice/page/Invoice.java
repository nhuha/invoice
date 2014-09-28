package com.nhuha.invoice.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.nhuha.invoice.SuperPage;

public class Invoice extends SuperPage {

	@FindBy(id = "product-name")
	private WebElement productName;
	@FindBy(id = "quantity")
	private WebElement quantity;
	@FindBy(id = "price")
	private WebElement price;
	@FindBy(id = "add-button")
	private WebElement addButton;
	@FindBy(className = "rows-wrapper")
	private WebElement rowsWrapper;
	@FindBy(css = ".wide-edit")
	private WebElement editProduct;
	@FindBy(xpath = "//div[@class='tr-cell quantity editable-cell']/input")
	private WebElement editQuantity;
	@FindBy(xpath = "//div[@class='tr-cell price editable-cell']/input")
	private WebElement editPrice;
	@FindBy(xpath = "//select[@class='edit-input narrow-edit']/option[text()='m']")
	private WebElement editUnit;
	@FindBy(xpath = "//img[contains(@src, 'images/accept.png')]")
	private WebElement acceptChangeButton;
	@FindBy(xpath = "//img[contains(@src, 'images/cancel.png')]")
	private WebElement cancelChangeButton;

	public Invoice(WebDriver driver) {
		super(driver);
	}

	public void emptyFielsValidation() {
		addButton.click();

	}

	public void productNameValidation(String name) {
		productName.clear();
		productName.sendKeys(name);
		quantity.sendKeys("1");
		price.sendKeys("1");
		addButton.click();

	}

	public void priceValidation(String name) {
		price.clear();
		price.sendKeys(name);
		quantity.sendKeys("1");
		productName.sendKeys("111");
		addButton.click();
	}

	public void quantityValidation(String name) {
		quantity.clear();
		quantity.sendKeys(name);
		price.sendKeys("1");
		productName.sendKeys("111");
		addButton.click();
	}

	public void addProduct(String name, String pr, String quan) {
		quantity.clear();
		price.clear();
		productName.clear();
		productName.sendKeys(name);
		price.sendKeys(pr);
		quantity.sendKeys(quan);
		addButton.click();

	}

	public void editProductName(String name) {
		editProduct.clear();
		editProduct.sendKeys(name);
		acceptChanges();

	}

	public void editQuantity(String q) {
		editQuantity.clear();
		editQuantity.sendKeys(q);
		acceptChanges();
	}

	public void editPrice(String q) {
		editPrice.clear();
		editPrice.sendKeys(q);
		acceptChangeButton.click();
	}

	public void acceptChanges() {
		acceptChangeButton.click();
	}

	public void cancelEditedChanges(String n, String q, String p) {
		editProduct.clear();
		editProduct.sendKeys(n);
		editQuantity.clear();
		editQuantity.sendKeys(q);
		editPrice.clear();
		editPrice.sendKeys(p);
		cancelChangeButton.click();
	}

	public void editProductDropDown(String name, String pr, String quan) {
		quantity.clear();
		price.clear();
		productName.clear();
		productName.sendKeys(name);
		price.sendKeys(pr);
		quantity.sendKeys(quan);

	}

	public List<WebElement> getListTableRow() {
		List<WebElement> tableRows = getRowsWrapper().findElements(ByClassName.className("table-row"));
		return tableRows;

	}

	public WebElement getProducName() {
		return productName;
	}

	public WebElement getQuantity() {
		return quantity;
	}

	public WebElement getPrice() {
		return price;
	}

	public WebElement getAddButton() {
		return addButton;
	}

	public WebElement getRowsWrapper() {
		return rowsWrapper;
	}

	public WebElement getEditProducName() {
		return editProduct;
	}

	public WebElement getEditPrice() {
		return editPrice;
	}

	public WebElement getEditQuantity() {
		return editQuantity;
	}

	public WebElement getEditButton() {
		String divPosition = String.valueOf(getListTableRow().size());
		WebElement editButton = driver.findElement(By.xpath("/html/body/div/div/div[" + divPosition + "]/div/img[1]"));
		return editButton;
	}

	public Select select() {
		Select select = new Select(driver.findElement(By.id("units")));

		return select;
	}

	public Select editUnitSelect() {
		Select editUnitselect = new Select(driver.findElement(By.xpath("//select[@class='edit-input narrow-edit']")));

		return editUnitselect;
	}

}
