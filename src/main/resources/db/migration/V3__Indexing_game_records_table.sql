-- idx_score_created_at 인덱스 추가
CREATE INDEX idx_score_created_at ON game_records (score DESC, created_at ASC);
