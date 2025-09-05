CREATE TABLE products(
    id BIGSERIAL  PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    sale_price DECIMAL(10, 2),
    original_price DECIMAL(10, 2),
    stock INTEGER DEFAULT 0,
    is_active BOOLEAN DEFAULT TRUE,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);

CREATE TABLE categories(
    id BIGSERIAL  PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE product_categories (
    product_id BIGINT  REFERENCES products(id) ON DELETE CASCADE,
    category_id BIGINT  REFERENCES categories(id) ON DELETE CASCADE,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (product_id, category_id)
);


CREATE TABLE product_media (
    id BIGSERIAL  PRIMARY KEY,
    product_id BIGINT NOT NULL REFERENCES products(id) ON DELETE CASCADE,
    url TEXT NOT NULL,
    type VARCHAR(50) NOT NULL,
    alt_text TEXT,
    is_primary BOOLEAN DEFAULT FALSE,
    sort_order INT DEFAULT 0,
    is_active BOOLEAN DEFAULT TRUE,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);