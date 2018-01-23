package zan.ind.productionmanager.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zan.ind.productionmanager.model.Product;
import zan.ind.productionmanager.repository.ProductRepository;

@RestController
@RequestMapping("/product-manager")
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	// Get All Products
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	// Create a new Product
	@PostMapping("/products")
	public Product createProduct(@Valid @RequestBody Product product) {
		return productRepository.save(product);
	}

	// Get a Single Product
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId) {
		Product product = productRepository.findOne(productId);
		if (product == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(product);
	}

	// Update a Product
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long productId,
			@Valid @RequestBody Product productDetails) {
		Product product = productRepository.findOne(productId);
		if (product == null) {
			return ResponseEntity.notFound().build();
		}
		product.setName(productDetails.getName());
		// product.setContent(productDetails.getContent());

		Product updatedProduct = productRepository.save(product);
		return ResponseEntity.ok(updatedProduct);
	}

	// Delete a Product
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable(value = "id") Long productId) {
		Product product = productRepository.findOne(productId);
		if (product == null) {
			return ResponseEntity.notFound().build();
		}

		productRepository.delete(product);
		return ResponseEntity.ok().build();
	}

}
