package com.com.LeandroCosta.appVendas.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> erros = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);

	}

	public List<FieldMessage> getErro() {
		return erros;
	}

	public void addErro(String fieldName, String mensagem) {
		erros.add(new FieldMessage(fieldName, mensagem));
	}
}
