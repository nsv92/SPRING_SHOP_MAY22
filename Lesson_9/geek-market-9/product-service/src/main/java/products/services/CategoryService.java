package products.services;

import products.repositories.CategoryRepository;
import contract.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category getCategory(Long id) {
		return categoryRepository.findById(id).orElse(null);
	}
	
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
}
