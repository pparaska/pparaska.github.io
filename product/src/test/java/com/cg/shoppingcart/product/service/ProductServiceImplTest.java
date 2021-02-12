package com.cg.shoppingcart.product.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.cg.shoppingcart.product.model.Product;
import com.cg.shoppingcart.product.model.ProductPrice;
import com.cg.shoppingcart.product.repository.ProductRepositoryImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ProductRepositoryImpl.class, ProductServiceImpl.class, Product.class })
@PowerMockIgnore("javax.management.*")
public class ProductServiceImplTest {

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	@Mock
	private ProductRepositoryImpl productRepositoryImpl;

	public ArrayList<Product> setUpProducts() {
		ArrayList<Product> products = new ArrayList<>();

		products.add(new Product(11111, "Wireless-Charger", new ProductPrice("Euro", 7f),
				"Wireless charger with great reviws", "Electronics"));
		products.add(new Product(11112, "Headphone", new ProductPrice("Euro", 10f),
				"JBL Headphones with best product revies", "Electronics"));
		products.add(new Product(11113, "IPhone11", new ProductPrice("Euro", 700f), "IPhone11 with 25% discount",
				"Electronics"));
		products.add(
				new Product(11114, "Wireless Mouse", new ProductPrice("Euro", 11f), "Wireless-Mouse", "Electronics"));
		products.add(new Product(11115, "DryFruits Pack", new ProductPrice("Rupees", 702.5f), "Fresh dry fruits",
				"Grocery"));

		return products;

	}

	@Test
	public void getAllProductsTest() {

		when(productRepositoryImpl.getAllProducts()).thenReturn((setUpProducts()));
		List<Product> productsFromService = productServiceImpl.getAllProducts();
		Assert.assertEquals(productsFromService, setUpProducts());
	}

	@Test
	public void getProductByIdTest() {
		when(productRepositoryImpl.getProductsByProductId(11111)).thenReturn(setUpProducts().get(0));
		Product product = productServiceImpl.getProductById(11111);
		assertThat(product.getProductId()).isEqualTo(11111);
	}

	@Test
	public void addNewProductTest() {
		Product product = setUpProducts().get(0);
		productServiceImpl.addNewProduct(product, "admin");
		assertThat(product.getProductName()).isEqualTo("Wireless-Charger");
	}

	@Test
	public void updateExistingProductTest() {
		Product product = setUpProducts().get(0);
		productServiceImpl.updateProductById(product.getProductId(), "admin", product);
		assertThat(product.getProductName()).isEqualTo("Wireless-Charger");
	}

	@Test
	public void deleteProductById() {
		Product product = setUpProducts().get(0);
		Integer productId = 11112;
		productServiceImpl.deleteProductById(productId, "admin");
		assertThat(product.getProductName()).isEqualTo("Wireless-Charger");

	}

}
