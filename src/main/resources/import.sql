insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Madeiro', 15.30, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Coco Bambu', 20.15, 2);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Tuk Tuk Comida Indiana', 0, 2);

insert into forma_pagamento (id, descricao) values (1, "Cartão de Crédito");
insert into forma_pagamento (id, descricao) values (2, "Cartão de Débito");
insert into forma_pagamento (id, descricao) values (3, "Dinheiro");

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permitir consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permitir editar cozinhas');

insert into estado (id, nome) values (1, 'Distrito Federal');
insert into estado (id, nome) values (2, 'Goias');

insert into cidade (id, nome, estado_id) values (1, 'Águas Claras', 1);
insert into cidade (id, nome, estado_id) values (2, 'Taguatinga', 1);
insert into cidade (id, nome, estado_id) values (3, 'Goiania', 2);
insert into cidade (id, nome, estado_id) values (4, 'Valparaiso', 2);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);






