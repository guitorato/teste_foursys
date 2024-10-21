CREATE TABLE LOG_INFO (
                          id_log SERIAL PRIMARY KEY,
                          user_atualz VARCHAR(255),
                          tp_status INT,
                          dt_atualz TIMESTAMP,
                          ds_response VARCHAR(1000),
                          ds_request VARCHAR(255)
);