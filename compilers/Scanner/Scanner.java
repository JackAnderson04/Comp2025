import java.util.*;
import java.io.PushbackReader;

public class Scanner 
{
    Map<String,String> reservedWords;
    Map<Character, String> tokens;
    Map<Character, String> XinuHuHu;
    Map<Character, String> whiteSpace;
    


    public enum State 
    {
        START, ACCEPT, ERR, ID, NUM, PUNCT, WHITESPACE    
    }
    
    public enum CharType
    {
        LETTER, DIGIT, PUNCT, WHITESPACE, OTHER 
    }
    public CharType characterClass[];

    // 0,1,2,3

    /*
      State|   Letter  |  Digit  |   Punct   |   WhiteSpace    |   Other
    ------------------------------------------------------------------------
      Start|   ID      |  Num    |   Punct   |   WhiteSpace    |   Err
    ------------------------------------------------------------------------
     Accept|   Start   |  Start  |   Start   |   Start         |   Start
    ------------------------------------------------------------------------
      Error|   Err     |  Err    |   Err     |   Err           |   Err
    */


    public State[][] edges = 
    {
        /* START */  
        {
            State.ID, // LETTER
            State.NUM, // DIGIT
            State.PUNCT, // PUNCT
            State.WHITESPACE, // WHITESPACE
            State.ERR // OTHER
        },
        /* ACCEPT */  
        {
            State.START, // LETTER
            State.START, // DIGIT
            State.START, // PUNCT
            State.START, // WHITESPACE
            State.START // OTHER
        },
        /* ERROR or INVALID STATE */
        {
            State.ERR, // LETTER
            State.ERR, // DIGIT
            State.ERR, // PUNCT
            State.ERR, // WHITESPACE
            State.ERR // OTHER
        },
    };

    public Scanner() {
        characterClass = new CharType[256]; 

        for(int i = 0; i < characterClass.length; i++){
          char c = (char) i; 
          
          if(Character.isLetter(c)){
            characterClass[i] = CharType.LETTER;
          }else if(Character.isDigit(c)){
            characterClass[i] = CharType.DIGIT;
          }else if(Character.isWhitespace(c)){
            characterClass[i] = CharType.WHITESPACE;
          }else if(Character.getType(c) == Character.END_PUNCTUATION 
                  || Character.getType(c) == Character.CONNECTOR_PUNCTUATION
                  || Character.getType(c) == Character.DASH_PUNCTUATION
                  || Character.getType(c) == Character.START_PUNCTUATION
                  || Character.getType(c) == Character.OTHER_PUNCTUATION){
            characterClass[i] = CharType.PUNCT;
          }else{
            characterClass[i] = CharType.OTHER;
          }
        }
      }

    public String reader(java.io.Reader reader) throws java.io.IOException
    {
        boolean debug=false;
        PushbackReader pushbackReader = new PushbackReader(reader);
        StringBuilder pretoken = new StringBuilder(); 
        String token = null;; 
        int c; 
        CharType type; 
        State state = State.START;
      
        while((c = reader.read()) != -1 ){
          type = characterClass[c];
          
          switch(state){
        
            case START:
              c = reader.read();
              type = characterClass[c];
              state = pretoken.append((char) c);

              if (state == State.WHITESPACE) {
                pretoken.setLength(0);  
                state = State.START;  
              }
              break;

            case ACCEPT:
              return pretoken.toString();  
              break;

            case ERR:
              return "Error: Invalid input";
              break;

            case ID:
              if (type == CharType.LETTER) {
                pretoken.append((char) c);  
              } else {
                pushbackReader.unread(c);  
                return pretoken.toString();  
              }
              break;

            case NUM:
              if (type == CharType.DIGIT) {
                pretoken.append((char) c);  
              } else {
                pushbackReader.unread(c);  
                return pretoken.toString();  
              }
              break;

            case PUNCT:
              return Character.toString((char) c);
              break;

            case WHITESPACE:
              state = State.START; 
              pretoken.setLength(0);  
              break;

          }
        }
        return token;
    }

    public static void main(String [] args) throws java.io.IOException
    {
      r.reservedWords.put("if", "IF");

      java.io.Reader reader=null;
      Scanner r = new Scanner();
        
    }
}