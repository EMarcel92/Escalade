BEGIN TRANSACTION;

--- ================================================================================
--- utilisateur
--- ================================================================================
	INSERT INTO public.utilisateur (utilisateurid, pseudo, mot_de_passe, nom_utilisateur, prenom_utilisateur, email) VALUES (	1, 'Manu', '$2a$11$tvAKvkKFXIcSKDB9yGNOyuKfyTPIg.Mt5MRsjMsYO0SCXe1teheHy', 'Marcel', 'Emmanuel', 'manu@axa.fr');
	INSERT INTO public.utilisateur (utilisateurid, pseudo, mot_de_passe, nom_utilisateur, prenom_utilisateur, email) VALUES (	2, 'Hugo', '$2a$11$tvAKvkKFXIcSKDB9yGNOyuKfyTPIg.Mt5MRsjMsYO0SCXe1teheHy', 'Les-Bons-Tuyaux', 'Hugo', 'hugo@axa.fr');
	
	SELECT setval('public.utilisateur_utilisateurid_seq', 2, true);

	
--- ================================================================================
--- role
--- ================================================================================
	INSERT INTO public.role (roleid, nom_role) VALUES (	1, 'admin');
	INSERT INTO public.role (roleid, nom_role) VALUES (	2, 'user');
	
	SELECT setval('public.utilisateur_utilisateurid_seq', 2, true);

	
--- ================================================================================
--- relation utilisateur role
--- ================================================================================
	INSERT INTO public.utilisateur_role (utilisateurid, roleid) VALUES (	1, 1);
	INSERT INTO public.utilisateur_role (utilisateurid, roleid) VALUES (	1, 2);
	INSERT INTO public.utilisateur_role (utilisateurid, roleid) VALUES (	2, 2);
	
	SELECT setval('public.utilisateur_utilisateurid_seq', 2, true);


--- ================================================================================
--- region
--- ================================================================================
	INSERT INTO public.region (regionid, nom_region) VALUES (	1,	'Ile de France'	);	
	INSERT INTO public.region (regionid, nom_region) VALUES (	2,	'Alpes'	);
	INSERT INTO public.region (regionid, nom_region) VALUES (	3,	'Pyrénées'	);
	
	SELECT setval('public.region_regionid_seq', 3, true);

	
--- ================================================================================
--- topo
--- ================================================================================
	INSERT INTO public.topo (topoid, nom_topo, description_topo, date_parution, disponible, regionid, utilisateurid, emprunteurid) 
		VALUES (	1, 'topo Alpes', 'Un super topo de Manu', '2019-04-01', 'I', 2, 1, 2);
	INSERT INTO public.topo (topoid, nom_topo, description_topo, date_parution, disponible, regionid, utilisateurid, emprunteurid) 
		VALUES (	2, 'Le Mercantour', 'les meilleurs spots du sud des Alpes', '2016-12-30', 'D', 2, 2, null);
	               
	SELECT setval('public.topo_topoid_seq', 2, true);
	
	
--- ================================================================================
--- site
--- ================================================================================
	INSERT INTO public.site (siteid, cotation_min, cotation_max, nom_site, description_site, photo, tag_officiel, regionid) VALUES (	1,	'3a', '9c', 'Gorges du Houx', 'Le site proche de Paris qui vous permet de grimper à tout âge.', 'GorgesDuHoux.png', true, 1);
	INSERT INTO public.site (siteid, cotation_min, cotation_max, nom_site, description_site, photo, tag_officiel, regionid) VALUES (	2,	'5b', '7c', 'Face nord de la Grande Arche', 'Ce site n''est légalement pas accessible. A vos risques et périls.','grandeArche.png' , false, 1);
	INSERT INTO public.site (siteid, cotation_min, cotation_max, nom_site, description_site, photo, tag_officiel, regionid) VALUES (	3,	'4c', '8a', 'Roubion', 'Proche de Nice, plus de 40 voies, du 4c au 8a , réparties sur plusieurs falaises.','Roubion.png' ,false, 2);
	INSERT INTO public.site (siteid, cotation_min, cotation_max, nom_site, description_site, photo, tag_officiel, regionid) VALUES (	4,	'3a', '5a', 'Vallon des Pelouses', 'Au pied du mont Viso et ses roches de couleur verte, un écrin magnifigue pour un site proposant de nombreuses variétés.', 'VallondesPelouses.png', false, 2);
	INSERT INTO public.site (siteid, cotation_min, cotation_max, nom_site, description_site, photo, tag_officiel, regionid) VALUES (	5,	NULL, NULL, 'Grottes de Sare', 'Un des rare sites des Pyrénées occidentales.', 'GrottesDeSare.png', true, 3);
	
	SELECT setval('public.commentaire_commentaireid_seq', 5, true);
	             
		
--- ================================================================================
--- commentaire 
--- ================================================================================
	INSERT INTO public.commentaire (commentaireid, texte_commentaire, date_redaction, utilisateurid, siteid) 
		VALUES (	1,	'J''aime pas ce site, c''est très glissant !', '2019-04-01 12:34:00', 1, 1);
	INSERT INTO public.commentaire (commentaireid, texte_commentaire, date_redaction, utilisateurid, siteid) 
		VALUES (	2,	'Moi, j''aime bien au contraire. C''est grand, naturel, frais et humide !', '2019-04-01 13:22:00', 2, 1);
	
	SELECT setval('public.commentaire_commentaireid_seq', 5, true);
	

--- ================================================================================
--- secteur
--- ================================================================================
	INSERT INTO public.secteur (secteurid, nom_secteur, siteid) VALUES (	1,	'Le Haut',	1);
	INSERT INTO public.secteur (secteurid, nom_secteur, siteid) VALUES (	2,	'La Vallée',	1);
	INSERT INTO public.secteur (secteurid, nom_secteur, siteid) VALUES (	3,	'les Gorges',	1);
			
	SELECT setval('public.secteur_secteurid_seq', 3, true);


--- ================================================================================
--- voie
--- ================================================================================
	INSERT INTO public.voie (voieid, nom_voie, cotation_voie, nb_points_voie, secteurid) VALUES (	1,	'le torticoli', '6c', 9, 1);
	INSERT INTO public.voie (voieid, nom_voie, cotation_voie, nb_points_voie, secteurid) VALUES (	2,	'le colimaçon', '8a', 0, 1);
	INSERT INTO public.voie (voieid, nom_voie, cotation_voie, nb_points_voie, secteurid) VALUES (	3,	'l''attrape-nigaud', '5b', 5, 1);
	
	SELECT setval('public.voie_voieid_seq', 3, true);

	
--- ================================================================================
--- longueur
--- ================================================================================
	INSERT INTO public.longueur (longueurid, nom_longueur, cotation_longueur, nb_points_longueur, voieid) VALUES (	1,	'le tortiqun', '6c', 3, 1);
	INSERT INTO public.longueur (longueurid, nom_longueur, cotation_longueur, nb_points_longueur, voieid) VALUES (	2,	'le tortideux', '5c', 2, 1);
	INSERT INTO public.longueur (longueurid, nom_longueur, cotation_longueur, nb_points_longueur, voieid) VALUES (	3,	'le tortitrois', '5a', 4, 1);
	INSERT INTO public.longueur (longueurid, nom_longueur, cotation_longueur, nb_points_longueur, voieid) VALUES (	4,	'l''escargot', '8a', 0, 2);
	INSERT INTO public.longueur (longueurid, nom_longueur, cotation_longueur, nb_points_longueur, voieid) VALUES (	5,  'la limace', '4a', 0, 2);
	
	SELECT setval('public.longueur_longueurid_seq', 5, true);

	
--- ROLLBACK;
 COMMIT;
