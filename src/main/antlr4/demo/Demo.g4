grammar Demo;

expression: left=expression '+' right=expression #Plus
          | num=NUMBER #LiteralNumber;

NUMBER: [0-9]+;
WHITE: [ \n\r\t]+ -> skip;