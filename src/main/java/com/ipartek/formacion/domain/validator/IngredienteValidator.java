package com.ipartek.formacion.domain.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.domain.form.IngredienteForm;

public class IngredienteValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(IngredienteForm.class);
	}
	
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "cantidad", "cantidad vacia");
		IngredienteForm ingrediente = (IngredienteForm) obj;
		// comprobar que el precio no esté vacío
		// (para que no haya null pointer más abajo)
		if (ingrediente == null)
			return;
		// comprobar que el número sea positivo
		if (ingrediente.getCantidad().length() < 1 || ingrediente.getCantidad().length() > 150 )
			errors.rejectValue("cantidad", "cantidadSizeNoVal");
	}




}
