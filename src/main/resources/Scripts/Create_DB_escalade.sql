
CREATE TABLE public.role (
                roleid INTEGER NOT NULL,
                nom_role VARCHAR(5),
                CONSTRAINT role_pk PRIMARY KEY (roleid)
);


CREATE SEQUENCE public.utilisateur_utilisateurid_seq;

CREATE TABLE public.utilisateur (
                utilisateurid INTEGER NOT NULL DEFAULT nextval('public.utilisateur_utilisateurid_seq'),
                pseudo VARCHAR(20) NOT NULL,
                mot_de_passe VARCHAR(100) NOT NULL,
                nom_utilisateur VARCHAR(40) NOT NULL,
                prenom_utilisateur VARCHAR(40) NOT NULL,
                email VARCHAR(50) NOT NULL,
                CONSTRAINT utilisateur_pk PRIMARY KEY (utilisateurid)
);


ALTER SEQUENCE public.utilisateur_utilisateurid_seq OWNED BY public.utilisateur.utilisateurid;

CREATE TABLE public.utilisateur_role (
                utilisateurid INTEGER NOT NULL,
                roleid INTEGER NOT NULL,
                CONSTRAINT utilisateur_role_pk PRIMARY KEY (utilisateurid, roleid)
);


CREATE SEQUENCE public.region_regionid_seq;

CREATE TABLE public.region (
                regionid INTEGER NOT NULL DEFAULT nextval('public.region_regionid_seq'),
                nom_region VARCHAR(40) NOT NULL,
                CONSTRAINT region_pk PRIMARY KEY (regionid)
);


ALTER SEQUENCE public.region_regionid_seq OWNED BY public.region.regionid;

CREATE SEQUENCE public.topo_topoid_seq;

CREATE TABLE public.topo (
                topoid INTEGER NOT NULL DEFAULT nextval('public.topo_topoid_seq'),
                nom_topo VARCHAR(50) NOT NULL,
                description_topo TEXT NOT NULL,
                date_parution DATE NOT NULL,
                disponible VARCHAR(1) NOT NULL,
                regionid INTEGER NOT NULL,
                utilisateurid INTEGER NOT NULL,
                emprunteurid INTEGER,
                CONSTRAINT topo_pk PRIMARY KEY (topoid)
);


ALTER SEQUENCE public.topo_topoid_seq OWNED BY public.topo.topoid;

CREATE SEQUENCE public.site_siteid_seq;

CREATE TABLE public.site (
                siteid INTEGER NOT NULL DEFAULT nextval('public.site_siteid_seq'),
                nom_site VARCHAR(50) NOT NULL,
                description_site TEXT NOT NULL,
                tag_officiel BOOLEAN NOT NULL,
                photo VARCHAR(50),
                regionid INTEGER NOT NULL,
                cotation_min VARCHAR(2),
                cotation_max VARCHAR(2),
                CONSTRAINT site_pk PRIMARY KEY (siteid)
);


ALTER SEQUENCE public.site_siteid_seq OWNED BY public.site.siteid;

CREATE SEQUENCE public.commentaire_commentaireid_seq;

CREATE TABLE public.commentaire (
                commentaireid INTEGER NOT NULL DEFAULT nextval('public.commentaire_commentaireid_seq'),
                texte_commentaire TEXT NOT NULL,
                date_redaction TIMESTAMP NOT NULL,
                utilisateurid INTEGER NOT NULL,
                siteid INTEGER NOT NULL,
                CONSTRAINT commentaire_pk PRIMARY KEY (commentaireid)
);


ALTER SEQUENCE public.commentaire_commentaireid_seq OWNED BY public.commentaire.commentaireid;

CREATE SEQUENCE public.secteur_secteurid_seq;

CREATE TABLE public.secteur (
                secteurid INTEGER NOT NULL DEFAULT nextval('public.secteur_secteurid_seq'),
                nom_secteur VARCHAR(50) NOT NULL,
                siteid INTEGER NOT NULL,
                CONSTRAINT secteur_pk PRIMARY KEY (secteurid)
);


ALTER SEQUENCE public.secteur_secteurid_seq OWNED BY public.secteur.secteurid;

CREATE SEQUENCE public.voie_voieid_seq;

CREATE TABLE public.voie (
                voieid INTEGER NOT NULL DEFAULT nextval('public.voie_voieid_seq'),
                nom_voie VARCHAR(50) NOT NULL,
                cotation_voie VARCHAR(2),
                nb_points_voie INTEGER NOT NULL,
                secteurid INTEGER NOT NULL,
                CONSTRAINT voie_pk PRIMARY KEY (voieid)
);


ALTER SEQUENCE public.voie_voieid_seq OWNED BY public.voie.voieid;

CREATE SEQUENCE public.longueur_longueurid_seq;

CREATE TABLE public.longueur (
                longueurid INTEGER NOT NULL DEFAULT nextval('public.longueur_longueurid_seq'),
                nom_longueur VARCHAR(50) NOT NULL,
                cotation_longueur VARCHAR(2),
                nb_points_longueur INTEGER NOT NULL,
                voieid INTEGER NOT NULL,
                CONSTRAINT longueur_pk PRIMARY KEY (longueurid)
);


ALTER SEQUENCE public.longueur_longueurid_seq OWNED BY public.longueur.longueurid;

ALTER TABLE public.utilisateur_role ADD CONSTRAINT role_utilisateur_role_fk
FOREIGN KEY (roleid)
REFERENCES public.role (roleid)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.topo ADD CONSTRAINT utilisateur_topo_fk
FOREIGN KEY (utilisateurid)
REFERENCES public.utilisateur (utilisateurid)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commentaire ADD CONSTRAINT utilisateur_commentaire_fk
FOREIGN KEY (utilisateurid)
REFERENCES public.utilisateur (utilisateurid)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.utilisateur_role ADD CONSTRAINT utilisateur_utilisateur_role_fk
FOREIGN KEY (utilisateurid)
REFERENCES public.utilisateur (utilisateurid)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.topo ADD CONSTRAINT utilisateur_topo_fk1
FOREIGN KEY (emprunteurid)
REFERENCES public.utilisateur (utilisateurid)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.site ADD CONSTRAINT region_site_fk
FOREIGN KEY (regionid)
REFERENCES public.region (regionid)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.topo ADD CONSTRAINT region_topo_fk
FOREIGN KEY (regionid)
REFERENCES public.region (regionid)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.secteur ADD CONSTRAINT site_secteur_fk
FOREIGN KEY (siteid)
REFERENCES public.site (siteid)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commentaire ADD CONSTRAINT site_commentaire_fk
FOREIGN KEY (siteid)
REFERENCES public.site (siteid)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.voie ADD CONSTRAINT secteur_voie_fk
FOREIGN KEY (secteurid)
REFERENCES public.secteur (secteurid)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.longueur ADD CONSTRAINT voie_longueur_fk
FOREIGN KEY (voieid)
REFERENCES public.voie (voieid)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
