CREATE TABLE category_section (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE category_section_item (
    id BIGSERIAL PRIMARY KEY,
    category_section_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    icon_url VARCHAR(512),
    is_active BOOLEAN DEFAULT TRUE,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_section FOREIGN KEY (category_section_id) REFERENCES category_section(id),
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories(id)
);
