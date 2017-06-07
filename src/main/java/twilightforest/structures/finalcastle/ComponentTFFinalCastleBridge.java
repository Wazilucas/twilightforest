package twilightforest.structures.finalcastle;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.structures.StructureTFComponent;
import java.util.List;
import java.util.Random;

public class ComponentTFFinalCastleBridge extends StructureTFComponent
{

	public ComponentTFFinalCastleBridge(int i, int x, int y, int z, int length, EnumFacing direction) {
		this.setCoordBaseMode(direction);
		this.boundingBox = StructureTFComponent.getComponentToAddBoundingBox2(x, y, z, 0, -1, -3, length - 1, 5, 6, direction);

	}

    @Override
    public void buildComponent(StructureComponent parent, List<StructureComponent> list, Random rand) {
	    if (parent != null && parent instanceof StructureTFComponent) {
		    this.deco = ((StructureTFComponent)parent).deco;
	    }
    }

	@Override
	public boolean addComponentParts(World world, Random rand, StructureBoundingBox sbb) {
		int length = (this.coordBaseMode == EnumFacing.SOUTH || this.coordBaseMode == EnumFacing.NORTH) ? this.boundingBox.maxX - this.boundingBox.minX : this.boundingBox.maxZ - this.boundingBox.minZ;

		// span
		fillWithRandomizedBlocks(world, sbb, 0, 0, 0, length, 1, 6, false, rand, deco.randomBlocks);
		// rails
		fillWithRandomizedBlocks(world, sbb, 0, 1, 0, length, 2, 0, false, rand, deco.randomBlocks);
		fillWithRandomizedBlocks(world, sbb, 0, 1, 6, length, 2, 6, false, rand, deco.randomBlocks);

		// supports
		int l3 = length / 3;
		for (int i = 0; i < l3; i++) {
			int sl = l3 - (int) (MathHelper.cos((float)(l3 - i) / (float)l3 * 1.6F) * (float)l3); // this could be better, maybe?
			fillWithRandomizedBlocks(world, sbb, i, -sl, 0, i, 0, 0, false, rand, deco.randomBlocks);
			fillWithRandomizedBlocks(world, sbb, i, -sl, 6, i, 0, 6, false, rand, deco.randomBlocks);
			fillWithRandomizedBlocks(world, sbb, length - i, -sl, 0, length - i, 0, 0, false, rand, deco.randomBlocks);
			fillWithRandomizedBlocks(world, sbb, length - i, -sl, 6, length - i, 0, 6, false, rand, deco.randomBlocks);
		}

		// doorframes
		this.fillWithBlocks(world, sbb, 0, 2, 1, 0, 7, 1, deco.pillarState, deco.pillarState, false);
		this.fillWithBlocks(world, sbb, 0, 2, 5, 0, 7, 5, deco.pillarState, deco.pillarState, false);
		this.fillWithBlocks(world, sbb, 0, 6, 2, 0, 6, 4, deco.accentState, deco.accentState, false);
		this.setBlockState(world, deco.pillarState, 0, 7, 3, sbb);

		this.fillWithBlocks(world, sbb, length, 2, 1, length, 7, 1, deco.pillarState, deco.pillarState, false);
		this.fillWithBlocks(world, sbb, length, 2, 5, length, 7, 5, deco.pillarState, deco.pillarState, false);
		this.fillWithBlocks(world, sbb, length, 6, 2, length, 6, 4, deco.accentState, deco.accentState, false);
		this.setBlockState(world, deco.pillarState, length, 7, 3, sbb);

		return true;
	}

}
