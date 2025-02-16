CREATE TABLE public.account_entity (
                                       id int8 NOT NULL,
                                       "number" varchar(255) NULL,
                                       "type" varchar(255) NULL,
                                       CONSTRAINT account_entity_pkey PRIMARY KEY (id)
);

CREATE TABLE public.document_entity (
                                        id int8 NOT NULL,
                                        "number" varchar(255) NULL,
                                        "type" varchar(255) NULL,
                                        CONSTRAINT document_entity_pkey PRIMARY KEY (id)
);

CREATE TABLE public.payment_data_entity (
                                            account_id int8 NULL,
                                            document_id int8 NULL,
                                            id int8 NOT NULL,
                                            amount varchar(255) NULL,
                                            bank varchar(255) NULL,
                                            "name" varchar(255) NULL,
                                            reference varchar(255) NULL,
                                            CONSTRAINT payment_data_entity_pkey PRIMARY KEY (id),
                                            CONSTRAINT fkdclmfpap8819m2g0bmxldn28p FOREIGN KEY (account_id) REFERENCES public.account_entity (id),
                                            CONSTRAINT fkmjyg61rpacdc0977jb8rie65f FOREIGN KEY (document_id) REFERENCES public.document_entity (id)
);