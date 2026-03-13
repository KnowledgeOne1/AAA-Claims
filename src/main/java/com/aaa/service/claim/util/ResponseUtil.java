package com.aaa.service.claim.util;

import static com.aaa.service.claim.util.StatusConstants.STATUS_CODE_SUCCESS;
import static com.aaa.service.claim.util.StatusConstants.STATUS_MESSAGE_SUCCESS;

import java.util.List;

import com.aaa.service.claim.model.Claim;
import com.aaa.service.claim.response.Response;
import com.aaa.service.claim.response.ResponseList;
import com.aaa.service.claim.response.Status;

public class ResponseUtil {

	public static <T> Response mapResponse(Class<T> r, Claim claim)
			throws InstantiationException, IllegalAccessException {
		Response response = (Response) r.newInstance();
		response.setClaim(claim);
		response.setStatus(getSuccessStatus());
		return response;
	}
	
	public static <T> ResponseList mapResponseList(Class<T> r, List<Claim> claim)
			throws InstantiationException, IllegalAccessException {
		ResponseList response = (ResponseList) r.newInstance();
		response.setClaims(claim);
		response.setStatus(getSuccessStatus());
		return response;
	}
	
	public static Status getSuccessStatus() {
		return Status.builder()
			.statusCode(STATUS_CODE_SUCCESS)
			.statusMessage(STATUS_MESSAGE_SUCCESS)
			.build();
	}

}
