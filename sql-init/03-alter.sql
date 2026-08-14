-- Quantidade no carrinho e nos itens de pedido
ALTER TABLE carts ADD COLUMN IF NOT EXISTS quantity INTEGER NOT NULL DEFAULT 1 CHECK (quantity > 0);
ALTER TABLE order_items ADD COLUMN IF NOT EXISTS quantity INTEGER NOT NULL DEFAULT 1 CHECK (quantity > 0);

-- Frete mockado
ALTER TABLE orders ADD COLUMN IF NOT EXISTS shipping_price NUMERIC(10,2) NOT NULL DEFAULT 0;

-- Cupom de desconto
CREATE TABLE IF NOT EXISTS coupons (
  id BIGSERIAL PRIMARY KEY,
  code VARCHAR(30) UNIQUE NOT NULL,
  discount_percent NUMERIC(5,2) NOT NULL CHECK (discount_percent > 0 AND discount_percent <= 100)
);
ALTER TABLE orders ADD COLUMN IF NOT EXISTS coupon_code VARCHAR(30);

INSERT INTO coupons (code, discount_percent) VALUES ('RODOLFO', 60)
ON CONFLICT (code) DO NOTHING;
