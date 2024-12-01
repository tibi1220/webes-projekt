-- Password is 'password'
INSERT INTO "user" (username, email, password) VALUES 
  ('admin', 'admin@admin.com', '$2a$12$DuI772XaK4LYX.TR8Q57ae/WhFhGdjdy.qqSubnWQGV69LYOi3ani'),
  ('john_doe', 'john.doe@example.com', '$2a$12$DuI772XaK4LYX.TR8Q57ae/WhFhGdjdy.qqSubnWQGV69LYOi3ani'),
  ('jane_smith', 'jane.smith@example.com', '$2a$12$DuI772XaK4LYX.TR8Q57ae/WhFhGdjdy.qqSubnWQGV69LYOi3ani'),
  ('alice_jones', 'alice.jones@example.com', '$2a$12$DuI772XaK4LYX.TR8Q57ae/WhFhGdjdy.qqSubnWQGV69LYOi3ani'),
  ('bob_brown', 'bob.brown@example.com', '$2a$12$DuI772XaK4LYX.TR8Q57ae/WhFhGdjdy.qqSubnWQGV69LYOi3ani'),
  ('charlie_white', 'charlie.white@example.com', '$2a$12$DuI772XaK4LYX.TR8Q57ae/WhFhGdjdy.qqSubnWQGV69LYOi3ani'),
  ('david_clark', 'david.clark@example.com', '$2a$12$DuI772XaK4LYX.TR8Q57ae/WhFhGdjdy.qqSubnWQGV69LYOi3ani'),
  ('emily_davis', 'emily.davis@example.com', '$2a$12$DuI772XaK4LYX.TR8Q57ae/WhFhGdjdy.qqSubnWQGV69LYOi3ani'),
  ('frank_miller', 'frank.miller@example.com', '$2a$12$DuI772XaK4LYX.TR8Q57ae/WhFhGdjdy.qqSubnWQGV69LYOi3ani'),
  ('george_wilson', 'george.wilson@example.com', '$2a$12$DuI772XaK4LYX.TR8Q57ae/WhFhGdjdy.qqSubnWQGV69LYOi3ani'),
  ('hannah_moore', 'hannah.moore@example.com', '$2a$12$DuI772XaK4LYX.TR8Q57ae/WhFhGdjdy.qqSubnWQGV69LYOi3ani'),
  ('ian_taylor', 'ian.taylor@example.com', '$2a$12$DuI772XaK4LYX.TR8Q57ae/WhFhGdjdy.qqSubnWQGV69LYOi3ani'),
  ('julia_martin', 'julia.martin@example.com', '$2a$12$DuI772XaK4LYX.TR8Q57ae/WhFhGdjdy.qqSubnWQGV69LYOi3ani'),
  ('karen_lee', 'karen.lee@example.com', '$2a$12$DuI772XaK4LYX.TR8Q57ae/WhFhGdjdy.qqSubnWQGV69LYOi3ani'),
  ('larry_harris', 'larry.harris@example.com', '$2a$12$DuI772XaK4LYX.TR8Q57ae/WhFhGdjdy.qqSubnWQGV69LYOi3ani'),
  ('maria_roberts', 'maria.roberts@example.com', '$2a$12$DuI772XaK4LYX.TR8Q57ae/WhFhGdjdy.qqSubnWQGV69LYOi3ani'),
  ('nathan_scott', 'nathan.scott@example.com', '$2a$12$DuI772XaK4LYX.TR8Q57ae/WhFhGdjdy.qqSubnWQGV69LYOi3ani'),
  ('olivia_turner', 'olivia.turner@example.com', '$2a$12$DuI772XaK4LYX.TR8Q57ae/WhFhGdjdy.qqSubnWQGV69LYOi3ani'),
  ('peter_wright', 'peter.wright@example.com', '$2a$12$DuI772XaK4LYX.TR8Q57ae/WhFhGdjdy.qqSubnWQGV69LYOi3ani'),
  ('quincy_evans', 'quincy.evans@example.com', '$2a$12$DuI772XaK4LYX.TR8Q57ae/WhFhGdjdy.qqSubnWQGV69LYOi3ani');


INSERT INTO product (name, description, price) VALUES 
  ('Laptop', 'High-performance laptop with 16GB RAM and 512GB SSD.', 999.99),
  ('Smartphone', 'Latest model smartphone with OLED display and dual camera.', 799.99),
  ('Wireless Headphones', 'Noise-cancelling wireless headphones with 20-hour battery life.', 199.99),
  ('Smartwatch', 'Water-resistant smartwatch with heart-rate monitor and GPS.', 249.50),
  ('Gaming Console', 'Next-gen gaming console with 4K support and 1TB storage.', 499.99),
  ('Electric Kettle', '1.7-liter electric kettle with temperature control.', 45.75),
  ('Bluetooth Speaker', 'Portable Bluetooth speaker with rich bass and 12-hour playtime.', 89.99),
  ('4K TV', '55-inch 4K UHD TV with HDR support and smart functionality.', 699.00),
  ('Digital Camera', '24MP digital camera with optical zoom and 4K video recording.', 450.00),
  ('Tablet', '10-inch tablet with stylus support and 64GB storage.', 299.99),
  ('Office Chair', 'Ergonomic office chair with lumbar support and adjustable height.', 150.00),
  ('Coffee Maker', 'Automatic coffee maker with built-in grinder and milk frother.', 120.00),
  ('Air Purifier', 'HEPA air purifier for medium-sized rooms.', 85.99),
  ('Blender', 'High-speed blender with multiple speed settings and smoothie mode.', 59.99),
  ('Electric Scooter', 'Foldable electric scooter with a 25km range.', 350.00),
  ('Smart Light Bulb', 'Wi-Fi enabled smart light bulb with adjustable color temperature.', 24.99),
  ('VR Headset', 'Wireless VR headset with 6DOF tracking and integrated audio.', 299.00),
  ('Desk Lamp', 'LED desk lamp with adjustable brightness and color.', 30.00),
  ('Portable Charger', '10000mAh portable charger with dual USB ports.', 29.50),
  ('Fitness Tracker', 'Waterproof fitness tracker with sleep and activity monitoring.', 55.00);

INSERT INTO "review" (rating, title, content, created_at, user_id, product_id) VALUES 
  (5, 'Amazing Product!', 'I am extremely satisfied with this product. Highly recommend!', '2024-11-01 14:32:00', 2, 1),
  (4, 'Very Good', 'The product is great but could use some improvements in build quality.', '2024-11-02 09:15:23', 2, 2),
  (3, 'Average Experience', 'It’s decent for the price, but not exactly what I expected.', '2024-11-03 18:45:12', 3, 3),
  (5, 'Exceeded Expectations!', 'The product works perfectly and has all the features I need.', '2024-11-05 11:22:45', 4, 4),
  (2, 'Not Worth the Price', 'The quality is not up to the mark, especially for the cost.', '2024-11-06 13:30:55', 5, 5),
  (4, 'Solid Performance', 'Great value for money. Performs well in everyday tasks.', '2024-11-08 07:20:10', 6, 6),
  (1, 'Terrible', 'Completely dissatisfied. The product stopped working after a week.', '2024-11-09 20:02:34', 7, 7),
  (5, 'Best Purchase Ever', 'Absolutely love this product! Highly recommended for everyone.', '2024-11-10 16:18:27', 8, 8),
  (3, 'Good but not Great', 'Overall, it’s fine, but there are better options available.', '2024-11-12 10:08:50', 9, 9),
  (4, 'Good Quality', 'Quality is good and meets my expectations.', '2024-11-13 12:40:00', 10, 10),
  (5, 'Fantastic', 'Amazing product! Well worth the investment.', '2024-11-15 13:50:12', 11, 1),
  (2, 'Disappointing', 'Does not meet the advertised specifications.', '2024-11-16 08:25:37', 12, 2),
  (3, 'It’s OK', 'An average product with some positive points.', '2024-11-17 19:44:00', 13, 3),
  (5, 'Top Quality', 'The best product I’ve bought in a long time. Works like a charm.', '2024-11-18 21:00:00', 14, 4),
  (4, 'Satisfied', 'Good value for money. I am happy with the purchase.', '2024-11-19 17:35:29', 15, 5),
  (3, 'Not Bad', 'Product is okay, but could use some updates.', '2024-11-20 13:18:47', 16, 6),
  (2, 'Low Quality', 'Disappointed with the build quality and performance.', '2024-11-21 14:30:00', 17, 7),
  (5, 'Perfect!', 'This product is exactly what I was looking for.', '2024-11-22 15:40:52', 18, 8),
  (4, 'Good Choice', 'I am pleased with the performance of this product.', '2024-11-22 20:45:18', 19, 9),
  (3, 'Could Be Better', 'It’s decent, but there are a few issues that need addressing.', '2024-11-23 09:05:00', 20, 10);

INSERT INTO "cart_item" (product_id, user_id, quantity) VALUES 
  (1, 1, 2),  -- User 1 has 2 units of Product 1 in their cart
  (2, 1, 1),  -- User 2 has 1 unit of Product 2 in their cart
  (3, 1, 5),  -- User 3 has 5 units of Product 3 in their cart
  (4, 1, 3),  -- User 4 has 3 units of Product 4 in their cart
  (5, 1, 2),  -- User 5 has 2 units of Product 5 in their cart
  (3, 6, 1),  -- User 6 has 1 unit of Product 6 in their cart
  (3, 7, 4),  -- User 7 has 4 units of Product 7 in their cart
  (3, 8, 2),  -- User 8 has 2 units of Product 8 in their cart
  (3, 9, 1),  -- User 9 has 1 unit of Product 9 in their cart
  (4, 10, 3),-- User 10 has 3 units of Product 10 in their cart
  (5, 11, 6), -- User 11 has 6 units of Product 1 in their cart
  (6, 12, 2), -- User 12 has 2 units of Product 3 in their cart
  (7, 13, 4), -- User 13 has 4 units of Product 4 in their cart
  (8, 14, 1), -- User 14 has 1 unit of Product 5 in their cart
  (8, 15, 7), -- User 15 has 7 units of Product 6 in their cart
  (8, 16, 3), -- User 16 has 3 units of Product 7 in their cart
  (8, 17, 5), -- User 17 has 5 units of Product 8 in their cart
  (9, 18, 2), -- User 18 has 2 units of Product 9 in their cart
  (10, 19, 1),-- User 19 has 1 unit of Product 10 in their cart
  (10, 20, 8); -- User 20 has 8 units of Product 2 in their cart

INSERT INTO "order" (user_id, order_date) VALUES 
  (1, '2024-11-23 10:15:00'),  -- Order by user with user_id 1
  (1, '2024-11-22 14:45:00'),  -- Order by user with user_id 2
  (1, '2024-11-21 16:30:00'),  -- Order by user with user_id 3
  (1, '2024-11-20 11:00:00'),  -- Another order by user with user_id 1
  (1, '2024-11-19 09:20:00');  -- Order by user with user_id 4

INSERT INTO "order_item" (order_id, product_id, quantity) VALUES 
(1, 1, 2),  -- 2 units of product with product_id 1 in order 1
(1, 3, 1),  -- 1 unit of product with product_id 3 in order 1

(2, 2, 3),  -- 3 units of product with product_id 2 in order 2

(3, 1, 1),  -- 1 unit of product with product_id 1 in order 3
(3, 4, 5),  -- 5 units of product with product_id 4 in order 3
(3, 5, 2),  -- 2 units of product with product_id 5 in order 3

(4, 3, 4),  -- 4 units of product with product_id 3 in order 4
(4, 2, 2),  -- 2 units of product with product_id 2 in order 4

(5, 5, 1);  -- 1 unit of product with product_id 5 in order 5