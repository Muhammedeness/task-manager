DROP TABLE IF EXISTS project_user;
DROP TABLE IF EXISTS projects;


CREATE TABLE projects (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),  -- UUID kullanman önerilir
    project_name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    state VARCHAR(50) NOT NULL DEFAULT 'ACTIVE'
);

CREATE TABLE project_user (
    project_id UUID NOT NULL,
    user_id UUID NOT NULL, -- Bu Keycloak'tan gelen user ID (sub claim)
    assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (project_id, user_id),
    FOREIGN KEY (project_id) REFERENCES projects(id)
    -- Dikkat: user_id için FOREIGN KEY yok çünkü userlar Keycloak’ta
);
