package com.aaa.service.claim.util;

import jakarta.persistence.EntityManager;

public class EntityUtil {

	public static <T> void saveEntity(EntityManager entityManager, T obj) {
		entityManager.persist(obj);
		entityManager.flush();
		entityManager.refresh(obj);
	}
	
	public static <T> void deleteEntity(EntityManager entityManager, T obj) {
		entityManager.merge(obj);
		entityManager.remove(obj);
		entityManager.flush();
	}
}
