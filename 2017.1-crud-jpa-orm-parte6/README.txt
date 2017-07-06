* Observações com relação ao "JPA 2.1 Entity Graph":
	- Foram utilizados "Set" em vez de "List" nas associações para evitar o erro do Hibernate: "javax.persistence.PersistenceException: org.hibernate.loader.MultipleBagFetchException: cannot simultaneously fetch multiple bags"
