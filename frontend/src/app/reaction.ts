export class Reaction {
  name:String;
  tags:String;
  image:String;
  id:String;

  constructor(id:String, name:String, image:String, tags:String) {
    this.name = name;
    this.tags = tags;
    this.image = image;
    this.id = id;
  }
  
}
