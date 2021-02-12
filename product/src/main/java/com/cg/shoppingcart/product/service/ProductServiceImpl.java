package com.cg.shoppingcart.product.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.shoppingcart.product.model.Product;
import com.cg.shoppingcart.product.model.UserRole;
import com.cg.shoppingcart.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	ArrayList<Product> products = new ArrayList<Product>();

	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	public List<UserRole> setUpUserRole() {
		List<UserRole> userRole = new ArrayList<>();
		userRole.add(new UserRole(1l, "admin"));
		userRole.add(new UserRole(2l, "user"));
		return userRole;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.getAllProducts();
	}

	@Override
	public Product getProductById(Integer productId) {
		logger.info("Inside getProductById method");

		return productRepository.getProductsByProductId(productId);
	}

	@Override
	public void updateProductById(Integer productId, String userRole, Product product) {
		List<UserRole> userRoles = setUpUserRole();
		for (UserRole userRole2 : userRoles) {
			if (userRole2.getRoleName().equalsIgnoreCase(userRole) && userRole.equalsIgnoreCase("admin")) {
				break;
			}
		}
		productRepository.updateProductByProductId(productId, product);
	}

	@Override
	public void deleteProductById(Integer productId, String userRole) {
		logger.info("Inside deleteProductById method");

		List<UserRole> userRoles = setUpUserRole();
		for (UserRole userRole2 : userRoles) {
			if (userRole2.getRoleName().equalsIgnoreCase(userRole) && userRole.equalsIgnoreCase("admin"))
				;
			break;
		}
		productRepository.deletProuctByProductId(productId);

	}

	@Override
	public void deleteAllProducts(String userRole) {
		logger.info("Inside deleteAllProducts method");

		List<UserRole> userRoles = setUpUserRole();
		for (UserRole userRole2 : userRoles) {
			if (userRole2.getRoleName().equalsIgnoreCase(userRole) && userRole.equalsIgnoreCase("admin"))
				;
			break;
		}
		productRepository.deleteAllProducts();
	}

	@Override
	public void addNewProduct(Product product, String userRole) {
		logger.info("Inside addNewProduct method");
		List<UserRole> userRoles = setUpUserRole();
		for (UserRole userRole2 : userRoles) {
			if (userRole2.getRoleName().equalsIgnoreCase(userRole) && userRole.equalsIgnoreCase("admin"))
				;
		}
		productRepository.addNewProduct(product);

		logger.info("Product {} added", product);

		;
	}
}
