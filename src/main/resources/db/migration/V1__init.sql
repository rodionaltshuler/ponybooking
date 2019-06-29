CREATE TABLE ponies (
  name VARCHAR(50) NOT NULL,
  available BOOLEAN DEFAULT true,
  returned_timestamp INT DEFAULT -1,
  recharge_time_mins INT DEFAULT 15,
  PRIMARY KEY (name)
);

INSERT INTO ponies (name) VALUES
('Pinky Pie');

INSERT INTO ponies (name) VALUES
('Rainbow Dash');

INSERT INTO ponies (name) VALUES
('Fluttershy');

INSERT INTO ponies (name, recharge_time_mins) VALUES
('Twilight Sparkle', 30);