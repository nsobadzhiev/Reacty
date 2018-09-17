export class Reaction {
  name:String;
  tags:String;
  image:String;
  link:String;
  id:String;

  constructor(id:String, name:String, image:String, tags:String, link:String) {
    this.name = name;
    this.tags = tags;
    this.image = image;
    this.id = id;
    this.link = link;
  }

}
