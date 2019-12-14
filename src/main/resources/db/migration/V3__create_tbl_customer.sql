CREATE TABLE public.customer (
  id BIGINT NOT NULL,
  actual_address_id BIGINT NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  middle_name VARCHAR(255) NOT NULL,
  registred_address_id BIGINT NOT NULL,
  sex VARCHAR(6) NOT NULL,
  CONSTRAINT customer_pkey PRIMARY KEY(id),
  CONSTRAINT customer_sex_check CHECK ((sex)::text = ANY ((ARRAY['MALE'::character varying, 'FEMALE'::character varying])::text[])),
  CONSTRAINT fk_actual_address_id FOREIGN KEY (actual_address_id)
    REFERENCES public.address(id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    NOT DEFERRABLE,
  CONSTRAINT fk_registred_address_id FOREIGN KEY (registred_address_id)
    REFERENCES public.address(id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    NOT DEFERRABLE
) ;
