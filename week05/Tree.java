public class Tree {
    public String Word;
    public String Type;
    public String Meaning;

    public Tree left;
    public Tree right;
    public Tree parent;

    public Tree(String Word, String Type, String Meaning){
        this.Word = Word;
        this.Type = Type;
        this.Meaning = Meaning;

        left = null;
        right = null;
        parent = null;
    }

    public String getWord(){
        return this.Word;
    }

    public String getType(){
        return this.Type;
    }

    public String getMeaning(){
        return this.Meaning;
    }



}
