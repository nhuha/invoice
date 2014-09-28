package com.nhuha.invoice.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nhuha.invoice.SuperTest;
import com.nhuha.invoice.page.Invoice;

public class Run extends SuperTest {
	Invoice invoicePage;

	@BeforeClass
	private void loginAdmin() {
		invoicePage = new Invoice(driver);
	}

	@Test
	public void emptyFieldsValidation() {
		invoicePage.emptyFielsValidation();

		Assert.assertTrue("input-field error".equals(invoicePage.getProducName().getAttribute("class")), "ProductName is not valid");
		Assert.assertTrue("input-field error".equals(invoicePage.getQuantity().getAttribute("class")), "Quantity is not valid");
		Assert.assertTrue("input-field error".equals(invoicePage.getPrice().getAttribute("class")), "Price is notvalid");
	}

	@Test(dataProvider = "stringFieldsProvider")
	public void productNameValidation(String name) {
		invoicePage.productNameValidation(name);

		Assert.assertTrue("input-field error".equals(invoicePage.getProducName().getAttribute("class")), "ProductName is not valid");

	}

	@Test(dataProvider = "quantityPriceProvider")
	public void priceValidation(String price) {
		invoicePage.priceValidation(price);

		Assert.assertTrue("input-field error".equals(invoicePage.getPrice().getAttribute("class")), "Price is notvalid");
	}

	@Test(dataProvider = "quantityPriceProvider")
	public void quantityValidation(String q) {
		invoicePage.quantityValidation(q);

		Assert.assertTrue("input-field error".equals(invoicePage.getQuantity().getAttribute("class")), "Quantity is not valid");

	}

	@Test
	public void addProductNameValidation() {
		invoicePage.addProduct(producName, price, quantity);
		invoicePage.addProduct(producName, price, quantity);
		invoicePage.addProduct(producName, price, quantity);

		Assert.assertTrue(
				invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1)
						.findElement(ByCssSelector.cssSelector(".tr-cell.product")).getText().equals(producName), "Product Name is correct");
	}

	@Test
	public void addProductNameValidatioONFalure() {
		invoicePage.addProduct(producName, price, quantity);
		invoicePage.addProduct(producName, price, quantity);
		invoicePage.addProduct(producName, price, quantity);

		Assert.assertTrue(
				invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1).findElement(ByCssSelector.cssSelector(".tr-cell.test"))
						.getText().equals(producName), "Product Name is correct");
	}

	@Test
	public void addProductPriceValidation() {
		invoicePage.addProduct(producName, price, quantity);
		invoicePage.addProduct("test2", "2", "1");
		invoicePage.addProduct(producName, price, quantity);

		Assert.assertTrue(
				invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1).findElement(ByCssSelector.cssSelector(".tr-cell.price"))
						.getText().equals(price), "Price is correct");
	}

	@Test
	public void addProductPriceQuantity() {
		invoicePage.addProduct(producName, price, quantity);
		invoicePage.addProduct("test2", "2", "1");
		invoicePage.addProduct(producName, price, quantity);

		Assert.assertTrue(
				invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1)
						.findElement(ByCssSelector.cssSelector(".tr-cell.quantity")).getText().equals(quantity), "Quantity is correct");
	}

	@Test
	public void addUnitsM2() {
		invoicePage.editProductDropDown("test", "1", "1");
		invoicePage.select().selectByVisibleText("m²");
		invoicePage.getAddButton().click();

		Assert.assertTrue(
				invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1).findElement(ByCssSelector.cssSelector(".tr-cell.units"))
						.getText().equals("m²"), "Units m² is correct");

	}

	@Test
	public void addUnitsM() {
		invoicePage.editProductDropDown("test", "1", "1");
		invoicePage.select().selectByVisibleText("m");
		invoicePage.getAddButton().click();

		Assert.assertTrue(
				invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1).findElement(ByCssSelector.cssSelector(".tr-cell.units"))
						.getText().equals("m"), "Units m is correct");

	}

	@Test
	public void addUnitsL() {
		invoicePage.editProductDropDown("test", "1", "1");
		invoicePage.select().selectByVisibleText("l");
		invoicePage.getAddButton().click();

		Assert.assertTrue(
				invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1).findElement(ByCssSelector.cssSelector(".tr-cell.units"))
						.getText().equals("l"), "Units l is correct");

	}

	@Test
	public void addUnitsKg() {
		invoicePage.editProductDropDown("test", "1", "1");
		invoicePage.select().selectByVisibleText("kg");
		invoicePage.getAddButton().click();

		Assert.assertTrue(
				invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1).findElement(ByCssSelector.cssSelector(".tr-cell.units"))
						.getText().equals("kg"), "Units kg is correct");

	}

	@Test
	public void addUnitsPcs() {
		invoicePage.editProductDropDown("test", "1", "1");
		invoicePage.select().selectByVisibleText("pcs");
		invoicePage.getAddButton().click();

		Assert.assertTrue(
				invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1).findElement(ByCssSelector.cssSelector(".tr-cell.units"))
						.getText().equals("pcs"), "Units pcs is correct");

	}

	@Test
	public void totalProductPriceVerification() {
		invoicePage.addProduct(producName, price, quantity);
		invoicePage.addProduct("test2", "2", "1");
		invoicePage.addProduct("333", "33.3", "3");

		String stringQuantity = invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1)
				.findElement(ByCssSelector.cssSelector(".tr-cell.quantity")).getText();
		int quantity = Integer.valueOf(stringQuantity);

		String stringPrice = invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1)
				.findElement(ByCssSelector.cssSelector(".tr-cell.price")).getText();
		double price = Double.valueOf(stringPrice);

		String total = String.valueOf(quantity * price);

		Assert.assertTrue(
				total.startsWith(invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1)
						.findElement(ByCssSelector.cssSelector(".tr-cell.total")).getText()), "Quantity is correct");

	}

	@Test
	public void totalPriceVerification() {
		invoicePage.addProduct(producName, price, quantity);
		invoicePage.addProduct("test2", "2", "1");
		invoicePage.addProduct("333", "33.3", "3");

		List<WebElement> totalPrices = invoicePage.getRowsWrapper().findElements(ByCssSelector.cssSelector(".tr-cell.total"));
		double pricesSum = 0;

		for (WebElement el : totalPrices) {
			String priceStr = el.getText();

			pricesSum += Double.valueOf(priceStr);

			System.out.println("pricesSum=" + pricesSum);
		}

	}

	@Test
	public void removeProduct() {
		invoicePage.addProduct("111", "222", "333");
		invoicePage.addProduct(producName, price, quantity);

		String divPosition = String.valueOf(invoicePage.getListTableRow().size());
		WebElement removeButton = driver.findElement(By.xpath("/html/body/div/div/div[" + divPosition + "]/div/img[2]"));

		removeButton.click();

		Assert.assertFalse(
				invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1)
						.findElement(ByCssSelector.cssSelector(".tr-cell.product")).getText().equals(producName), "Product Name is correct");
		Assert.assertFalse(
				invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1).findElement(ByCssSelector.cssSelector(".tr-cell.price"))
						.getText().equals(price), "Price is correct");

		Assert.assertFalse(
				invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1)
						.findElement(ByCssSelector.cssSelector(".tr-cell.quantity")).getText().equals(quantity), "Quantity is correct");

	}

	@Test
	public void editProduct() {
		invoicePage.addProduct("111", "222", "333");
		invoicePage.addProduct(producName, price, quantity);
		invoicePage.getEditButton().click();
		invoicePage.editProductName("EDIT");

		Assert.assertTrue(
				invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1)
						.findElement(ByCssSelector.cssSelector(".tr-cell.product")).getText().equals("EDIT"), "Product Name is correct");

	}

	@Test
	public void editQantity() {
		invoicePage.addProduct("111", "222", "333");
		invoicePage.addProduct(producName, price, quantity);
		invoicePage.getEditButton().click();
		invoicePage.editQuantity("5");

		Assert.assertTrue(
				invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1)
						.findElement(ByCssSelector.cssSelector(".tr-cell.quantity")).getText().equals("5"), "Quantity is correct");

	}

	@Test
	public void editPrice() {
		invoicePage.addProduct("111", "222", "333");
		invoicePage.addProduct(producName, price, quantity);
		invoicePage.getEditButton().click();
		invoicePage.editPrice("5.7");

		Assert.assertTrue(
				invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1).findElement(ByCssSelector.cssSelector(".tr-cell.price"))
						.getText().equals("5.7"), "Price is correct");

	}

	@Test
	public void editUnit() {
		invoicePage.addProduct("111", "222", "333");
		invoicePage.addProduct(producName, price, quantity);
		invoicePage.getEditButton().click();
		invoicePage.editUnitSelect().selectByVisibleText("m");
		invoicePage.acceptChanges();

		Assert.assertTrue(
				invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1).findElement(ByCssSelector.cssSelector(".tr-cell.units"))
						.getText().equals("m"), "Product Unit  is correct");

	}

	@Test(dataProvider = "stringFieldsProvider")
	public void editProductNameValidation(String name) {
		invoicePage.addProduct("111", "222", "333");
		invoicePage.addProduct(producName, price, quantity);
		invoicePage.getEditButton().click();
		invoicePage.editProductName(name);

		Assert.assertTrue("edit-input wide-edit error".equals(invoicePage.getEditProducName().getAttribute("class")), "ProductName is not valid");

	}

	@Test(dataProvider = "quantityPriceProvider")
	public void editPriceValidation(String price) {
		invoicePage.addProduct("111", "222", "333");
		invoicePage.addProduct(producName, price, quantity);
		invoicePage.getEditButton().click();
		invoicePage.editPrice(price);

		Assert.assertTrue("edit-input narrow-edit error".equals(invoicePage.getEditPrice().getAttribute("class")), "Price is not valid");

	}

	@Test(dataProvider = "quantityPriceProvider")
	public void editQuantityValidation(String q) {
		invoicePage.addProduct("111", "222", "333");
		invoicePage.addProduct(producName, price, quantity);
		invoicePage.getEditButton().click();
		invoicePage.editQuantity(q);

		Assert.assertTrue("edit-input narrow-edit error".equals(invoicePage.getEditQuantity().getAttribute("class")), "Price is not valid");

	}

	@Test
	public void cancelEditProduct() {
		invoicePage.addProduct("111", "222", "333");
		invoicePage.addProduct(producName, price, quantity);
		invoicePage.getEditButton().click();
		invoicePage.cancelEditedChanges("cancel", "23", "1");

		Assert.assertTrue(
				invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1)
						.findElement(ByCssSelector.cssSelector(".tr-cell.product")).getText().equals(producName), "Product Name is correct");
		Assert.assertTrue(
				invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1).findElement(ByCssSelector.cssSelector(".tr-cell.price"))
						.getText().equals(price), "Price is correct");

		Assert.assertTrue(
				invoicePage.getListTableRow().get(invoicePage.getListTableRow().size() - 1)
						.findElement(ByCssSelector.cssSelector(".tr-cell.quantity")).getText().equals(quantity), "Quantity is correct");

	}

}
