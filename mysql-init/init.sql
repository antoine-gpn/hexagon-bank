-- Sélectionner la base de données
USE exalt;

-- Création de la table Transaction
CREATE TABLE IF NOT EXISTS transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    amount DECIMAL(10,2) NOT NULL,
    operator VARCHAR(255) NOT NULL,
    label VARCHAR(255),
    date DATETIME NOT NULL DEFAULT NOW()
    );

-- Insertion de données initiales
INSERT INTO transaction (amount, operator, label, date) VALUES (12, '+', 'Paiement facture', '2025-02-21 14:30:00');
INSERT INTO transaction (amount, operator, label, date) VALUES (21, '-', 'Virement facture', '2025-02-22 12:15:00');
INSERT INTO transaction (amount, operator, label, date) VALUES (15, '+', 'Paiement facture', '2025-02-23 17:45:00');

