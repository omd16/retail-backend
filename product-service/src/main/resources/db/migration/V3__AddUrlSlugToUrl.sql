ALTER TABLE products ADD COLUMN url_slug VARCHAR(512) DEFAULT '/t' NOT NULL;
ALTER TABLE categories ADD COLUMN url_slug VARCHAR(512) DEFAULT '/t' NOT NULL;
ALTER TABLE category_section ADD COLUMN sort_order INTEGER;
ALTER TABLE category_section_item ADD COLUMN sort_order INTEGER;