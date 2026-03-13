SELECT distinct(pro.login_id)
FROM 
user.user_profile pro
	inner join
	user.user_skills sk
	on pro.profile_id = sk.profile_id
	inner join user.user_education edu
	on pro.profile_id = edu.profile_id
WHERE
pro.login_id like :value or
sk.name like :value or
sk.category = :value or
edu.area_of_study like :value;