CREATE TABLE IF NOT EXISTS feedbacks (
    id UUID PRIMARY KEY,
    user_id UUID,
    restaurant_id UUID,
    item_id UUID NULL,
    rating INT,
    comments TEXT,
    create_at TIMESTAMP,
    modify_at TIMESTAMP,
    modify_by VARCHAR(255),
    create_by VARCHAR(255)
);
