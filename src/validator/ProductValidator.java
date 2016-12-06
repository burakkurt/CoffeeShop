package validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import domain.Product;


@Component
public class ProductValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {

		Product product = (Product) arg0;
		Errors errors = (Errors) arg1;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name");

		if (product.getPrice() <= 0) {
			errors.rejectValue("price", "required.price");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required.description");
		
	}

}
