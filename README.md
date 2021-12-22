# SuperBubble Algorithm Application

##Installation

The only requirement to run the SuperBubble.jar is having java on your system.
Below is an easy way to install java:

(Source:https://www.ntu.edu.sg/home/ehchua/programming/howto/JDK_HowTo.html#jdk-install):
Either use link source above or(Below used from link above)...

1. Download latest JDK from the java website:https://www.oracle.com/technetwork/java/javase/downloads/index.html
2. Choose JDK for your operating system and downoad it.
3.  Install this JDK, and remember what the extension the jdk is (for example jdk-9.0.1).

To add to your PATH:

1. Launch ”Control Panel” -> (Optional) ”System and Security” -> ”System” -> Click ”Advanced system settings” 
on the left pane.
2. Switch to ”Advanced” tab -> Click ”Environment Variables” button.
3. Under  ”System  Variables”  (the  bottom  pane),  scroll down to select variable ”Path” -> Click ”Edit...”.
4. For newer windows 10:

    You  shall  see  a  TABLE  listing  all  the  existing  PATH  entries  (if  not,  goto  next  step).
Click  ”New”  -> Click  ”Browse”  and  navigate  to  your  JDK’s  ”bin”  directory,  i.e.,  “c:\ProgramFiles\Java\jdk-11.0.{x}\bin”, where x is your installation update number -> Select ”Move Up” to move this entry all the way to the TOP.
5. For older Windows 10:

    (To  be  SAFE,  copy  the  content  of  the  ”Variable  value”  to  Notepad  before changing it!!!) 
    In ”Variable value” field, APPEND “c:\ProgramFiles\Java\jdk-11.0.{x}\bin”(where x is your installation update number) 
    IN FRONT of all the existing directories,followed  by  a  semi-colon  (;)  to  separate  the  JDK’s  bin  directory 
    from  the  rest  of  the existing  directories. DO  NOT  DELETE  any  existing  entries;  otherwise,  some  existing 
    applications may not run.
    
##User Guide
**Creating your graph**

The first thing you must do before being able to generate your superbubble report is 
correctly inputting your graph. Methods for this are outlined below:

**Adding a vertex:**
1.  Click the “Add Vertex” button.  This will generate a vertex in the Vertexes list.

**Adding multiple vertexes:**
1. In  the  text  field  above  the  “Add  Vertexes”  button,  input  the  number  
of  vertexes  you would like to add.
2. Click the “Add Vertexes” button.  This will generate the number of vertexes 
specified in the Vertexes list.

**Adding a child to a vertex (Note: adding the child immediately adds the vertex
for which the child has been entered as a parent of the vertex):**
1. Double click on the vertex you would like to add a child to in the Vertexes list.
2. Make sure that the “Children of Vertex” list header is now updated to “Children of Vertex 
Vn” where n is the id of the vertex chosen.
3. Once the header is now correctly changed, enter the vertex you would like to add as a child of the selected vertex into the text field above the “Add Child” button.  (Note: make 
sure the vertex you enter is of the form “Vn” where n is the id of the vertex, and the 
vertex  is  a  member  of  the  Vertexes  list.   Appropriate  error  windows  will  arise  if  these aren’t abode to).
4. Click the “Add Child” button.  This will add the vertex as a child of the selected vertex and it will be visible in the Children list.

**Adding a parent to a vertex (Note: adding the parent immediately adds the vertex
for which the parent has been entered as a child of the vertex):**
1. Double click on the vertex you would like to add a parent to in the Vertexes list.
2. Make sure that the “Parents of Vertex” list header is now updated to “Parents of Vertex 
Vn” where n is the id of the vertex chosen.
3. Once the header is now correctly changed, enter the vertex you would like to add as a parent of the selected vertex into the text field above the “Add Parent” button.  (Note: make 
sure the vertex you enter is of the form “Vn” where n is the id of the vertex, and the 
vertex  is  a  member  of  the  Vertexes  list.   Appropriate  error  windows  will  arise  if  these aren’t abode to).
4. Click the “Add Parent” button.  This will add the vertex as a parent of the selected vertex and it will be visible in the Parents list.

**Deleting a child:**
1. Double click on the vertex you would like to remove a child from.
2. Make sure that the “Children of Vertex” list header is now updated to “Children of Vertex Vn” where n is the id of the vertex chosen.
3. Double click on the vertex you would like to be removed from the Children’s list.
4. Click the “Delete Child” button. Notice this removes the vertex from the Children’s list for that Vertex 
(Note:  It also removes the selected vertex as the parent of the removed child vertex).

**Deleting a parent:**
1. Double click on the vertex you would like to remove a parent from.
2. Make sure that the “Parent of Vertex” list header is now updated to “Children of Vertex Vn” where n is the id of the vertex chosen.
3. Double click on the vertex you would like to be removed from the Parents list.
4.  Click the “Delete Parent” button.  Notice this removes the vertex from the Parents list for
that Vertex (Note:  It also removes the selected vertex as the child of the removed parent vertex).

**Generating your superbubble report**

Once you finish entering the graph by generating all necessary vertexes and adding all necessary
children/parents for each vertex, by clicking the “Generate Super Bubble Report” button, you
will see that the “Super Bubble Report” list is updated with pairs of Super Bubbles. 
(Note: if  there  are  no  super  bubbles,  nothing  will  happen. Also  Note:  Only  create  the  amount  of
vertexes that belong to the graph.  Creating more vertexes currently causes the algorithm to
incorrectly generate the super bubble report).

**What to do if something goes wrong**

If you make a mistake with how many vertexes you have added, by clicking the “Reset” button,
the application will restart itself and you can begin entering data again with a clean sheet.