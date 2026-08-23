CREATE table device (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100),
    type VARCHAR(20) NOT NULL,
    user_Id BIGINT NOT NULL,
    constraint fk_device_user FOREIGN KEY (user_Id) REFERENCES app_user(id) ON DELETE CASCADE
);