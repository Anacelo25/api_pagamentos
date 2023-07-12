CREATE TABLE pagamento (

    id bigint not null auto_increment,
    codigododebito VARCHAR(100) not null,
    numerodocartao VARCHAR(100) not null,
    Cpf_Cnpj VARCHAR(100) not null unique,
    valordopagamento DECIMAL(10,2),
    metododepagamento VARCHAR(100) not null,
    status VARCHAR(100) not null,

    primary key(id)

);