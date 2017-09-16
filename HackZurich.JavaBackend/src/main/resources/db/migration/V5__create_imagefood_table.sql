create table ImageFood (
  id bigint not null primary key identity(1,1),
  url varchar(1024),
  fat numeric(10,5),
  calories numeric(10,5),
  sugar numeric(10,5),
  healthScore numeric(10,5),
  image_id bigint
);

GO

ALTER TABLE ImageFood
ADD CONSTRAINT FK_image_food FOREIGN KEY (image_id)
REFERENCES CleanFoodImage (id)
ON DELETE CASCADE
ON UPDATE CASCADE
;
